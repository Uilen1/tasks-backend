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
                dir('frontend') {
                    git credentialsId: 'gitLogin', url: 'https://github.com/Uilen1/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'tomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: '/tasks', war: '**/*.war'
                }
            }
        }

        stage ('Funcional Tests') {
            steps {
                dir('funcional-test') {
                    git credentialsId: 'gitLogin', url: 'https://github.com/Uilen1/tasks-functional-tests'
                    bat 'mvn test'
                }
            }
        }

        stage ('Deploy Prod') {
            steps {
                bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
    }
    post{
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, api-test/target/surefire-reports/*.xml, funcional-test/target/surefire-reports/*.xml'
        }

    }
}


