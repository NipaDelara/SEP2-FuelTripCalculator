
pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESS - Fuel Trip Calculator'
        }

        failure {
            echo 'BUILD FAILURE - Check console output'
        }

        always {
            junit allowEmptyResults: true,
                  testResults: '**/target/surefire-reports/*.xml'

            archiveArtifacts allowEmptyArchive: true,
                             artifacts: 'target/*.jar'
        }
    }
}
