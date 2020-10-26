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
                'mvn test -Dbrowser=chrome_remote'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}