pipeline {
    agent any

    environment {
        // Change group/artifact/version according to your project
        GROUP_ID = "com.scex"
        ARTIFACT_ID = "TestApp"
        VERSION = "1.0.0"
        JAR_NAME = "${ARTIFACT_ID}-${VERSION}.jar"
        NEXUS_URL = "http://localhost:8081/repository/maven-releases/"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YOUR_GITHUB_USERNAME/YOUR_REPO.git'
            }
        }

        stage('Build Fat Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Upload to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-creds',
                        usernameVariable: 'USER',
                        passwordVariable: 'PASS')]) {

                    sh """
                        echo "Uploading ${JAR_NAME} to Nexus..."

                        mvn deploy:deploy-file \
                            -DgroupId=${GROUP_ID} \
                            -DartifactId=${ARTIFACT_ID} \
                            -Dversion=${VERSION} \
                            -DgeneratePom=true \
                            -Dpackaging=jar \
                            -Dfile=target/${JAR_NAME} \
                            -DrepositoryId=nexus \
                            -Durl=${NEXUS_URL} \
                            -DrepositoryId=nexus \
                            -Durl=${NEXUS_URL} \
                            -Dusername=$USER \
                            -Dpassword=$PASS
                    """
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
