pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    triggers {
	cron('H/15 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                echo 'Building the Maven project and running tests...'
                sh 'mvn clean test'
            }

            post {
                always {
                    junit testResults: 'course-registration-app/target/surefire-reports/TEST-*.xml',
                          allowEmptyResults: false
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging...'
                sh 'mvn package'
            }
        }
    }

    post {
        success {

            echo 'Build, tests, and packaging completed'
        }

        failure {
            echo 'The pipeline failed'
        }

        always {
            echo 'Pipeline executed'
        }
    }
}
