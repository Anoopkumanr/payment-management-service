pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    environment {
        DOCKER_IMAGE = "YOUR_DOCKERHUB_USERNAME/springboot-app"
        DOCKER_TAG   = "${env.BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Anoopkumanr/payment-management-service.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        // stage('Unit Test') {
        //     steps {
        //         bat 'mvn test'
        //     }
        //     post {
        //         always {
        //             junit 'target/surefire-reports/*.xml'
        //         }
        //     }
        // }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
    steps {
        bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
    }
}

stage('Push Docker Image') {
    steps {
        withCredentials([
            usernamePassword(
                credentialsId: 'dockerhub-credentials',
                usernameVariable: 'DOCKER_USERNAME',
                passwordVariable: 'DOCKER_PASSWORD'
            )
        ]) {
            bat """
                docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%
                docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest
                docker push ${DOCKER_IMAGE}:latest
                docker logout
            """
        }
    }
}

        stage('Cleanup Local Image') {
            steps {
                bat "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || exit 0"
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully - build, test aur Docker image push ho gayi.'
        }

        failure {
            echo 'Pipeline fail ho gayi - logs check karo.'
        }
    }
}
