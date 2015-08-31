# gradle-beanstalk-plugin

Gradle plugin for deploying WARs to AWS Elastic Beanstalk.

## Usage

First, create `~/.aws/credentials` with a profile for your app:

    [my-profile]
    aws_access_key_id=YOUR_ACCESS_KEY_ID
    aws_secret_access_key=YOUR_SECRET_ACCESS_KEY

Next, configure some deployments in your `build.gradle`:

    plugins {
        id "fi.evident.beanstalk" version "0.0.5"
    }

    beanstalk {
        profile = 'my-profile'
        s3Endpoint = "s3-eu-west-1.amazonaws.com"
        beanstalkEndpoint = "elasticbeanstalk.eu-west-1.amazonaws.com"
        versionsToKeep = 20 // Optional, if set, old versions will be deleted
    
        deployments {
            staging {
                war = tasks.war
                application = 'my-app'
                environment = 'my-app-staging'
            }
    
            production {
                war = tasks.productionWar
                application = 'my-app'
                environment = 'my-app-production'
            }
        }
    }

Finally, deploy by running `gradle deploy<deployment-name>`, e.g. `gradle deployStaging`.
