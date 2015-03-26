# gradle-beanstalk-plugin

Gradle plugin for deploying WARs to AWS Elastic Beanstalk.

## Usage

First apply the plugin:
 
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath "fi.evident.gradle.beanstalk:gradle-beanstalk-plugin:0.0.2"
        }
    }
    apply plugin: 'fi.evident.beanstalk'

Create `~/.aws/credentials` with a profile for your app:

    [my-profile]
    aws_access_key_id=YOUR_ACCESS_KEY_ID
    aws_secret_access_key=YOUR_SECRET_ACCESS_KEY

Next, configure some deployments in your `build.gradle`:

    beanstalk {
        profile = 'my-profile'
        s3Endpoint = "s3-eu-west-1.amazonaws.com"
        beanstalkEndpoint = "elasticbeanstalk.eu-west-1.amazonaws.com"
    
        deployments {
            staging {
                war = devWar
                application = 'my-app'
                environment = 'my-app-staging'
            }
    
            production {
                war = productionWar
                application = 'my-app'
                environment = 'my-app-production'
            }
        }
    }

Finally, deploy by running `gradle deploy<deployment-name>`, e.g. `gradle deployStaging`.
