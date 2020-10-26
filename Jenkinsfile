pipeline {
    agent any

    stages {
        stage ('Initialize') {
            steps {

                    echo "PATH = %PATH%"
                    echo "M2_HOME = %M2_HOME%"

            }
        }

        stage ('Test') {
            steps {
                mvn test
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}