pipeline {
    agent any

    environment {
        // Change group/artifact/version according to your project
        GROUP_ID = "com.scex"
        ARTIFACT_ID = "TestApp"
        BASE_VERSION = "1.0"
        //JAR_NAME = "${ARTIFACT_ID}-${VERSION}.jar"
        BUILD_VERSION = "${BASE_VERSION}.${BUILD_NUMBER}"
        NEXUS_URL = "http://localhost:8081/repository/maven-releases/"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/adi-alidx321/TestApp.git'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Integration Test') {
            steps {
                sh 'mvn verify -DskipTests'
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Upload to Nexus') {
            steps {

                withCredentials([usernamePassword(
                        credentialsId: 'jenkin-nexus-cred',
                        usernameVariable: 'NEXUS_USERNAME',
                        passwordVariable: 'NEXUS_PASSWORD'
                )]) {

                    sh "echo $NEXUS_USERNAME"
                    sh "echo $NEXUS_PASSWORD"

                    configFileProvider([configFile(fileId: 'maven-settings-prod', variable: 'SETTINGS_XML')]) {
                        sh "mvn versions:set -DnewVersion=${BUILD_VERSION}"
                        sh """
                            echo "Uploading artifact using mvn deploy..."
                            export NEXUS_USERNAME=${NEXUS_USERNAME}
                            export NEXUS_PASSWORD=${NEXUS_PASSWORD}

                            mvn deploy \
                                --settings $SETTINGS_XML \
                                -DskipTests
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Build + Upload completed successfully!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
