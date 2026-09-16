pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Glueymetal/student-management.git'
            }
        }
        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t student-management:latest .'
            }
        }
        stage('Deploy') {
            steps {
                bat 'docker rm -f student-app || exit 0'
                bat 'docker run -d --name student-app student-management:latest'
            }
        }
    }
}