# gradle-beanstalk-plugin

Gradle plugin for deploying applications to AWS Elastic Beanstalk.

## Usage

First, create `~/.aws/credentials` with a profile for your app:

    [my-profile]
    aws_access_key_id=YOUR_ACCESS_KEY_ID
    aws_secret_access_key=YOUR_SECRET_ACCESS_KEY

Or set `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` environment variables.

Next, configure some deployments in your `build.gradle`:

    plugins {
        id "fi.evident.beanstalk" version "0.3.2"
    }

    // beanstalk requires unique version number for each uploaded archive
    // SNAPSHOT will be replaced with 'yyyyMMdd.HHmms' label
    version = '0.1-SNAPSHOT'

    beanstalk {
        profile = 'my-profile' // Only required if using .aws/credentials
        s3Endpoint = "s3-eu-west-1.amazonaws.com"
        beanstalkEndpoint = "elasticbeanstalk.eu-west-1.amazonaws.com"
    
        deployments {
            // Example to deploy to the same env
            staging {
                file = tasks.war
                application = 'my-app'
                environment = 'my-app-staging'
            }
            // Example to create a new env for each version (to use URL swapping for blue/green deployment)
            production {
                file = tasks.productionWar
                application = 'my-app'
                environment = "my-app-${project.version.replaceAll('\\.', '-')}"
                template = 'default' // Saved configuration name to use to create each env
            }
            // Example to upload a new version to an application without deploying it (to be able to
            //  manually pick which environment to use)
            upload {
                file = tasks.war
                application = 'my-app'
            }
        }
    }

Finally, deploy by running `gradle deploy<deployment-name>`, e.g. `gradle deployStaging`.
