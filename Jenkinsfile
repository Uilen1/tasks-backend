pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }

        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }

        stage ('API Tests') {
            steps {
                dir('api-test') {
                    git credentialsId: 'gitLogin', url: 'https://github.com/Uilen1/task-APITest'
                    bat 'mvn test'
                }
            }
        }

        stage ('Deploy Frontend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: '/tasks', war: '**/*.war'
            }
        }
    }
}


