pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Setup Java & Chrome') {
            steps {
                script {
                    sh '''
                        java -version
                        mvn -version
                        if ! command -v google-chrome >/dev/null 2>&1; then
                            echo "Google Chrome is not installed. Install it on the Jenkins agent before running Selenium tests."
                            exit 1
                        fi
                    '''
                }
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo 'Running Maven test suite...'
                sh 'mvn clean test -q'
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Publishing test reports...'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-html-reports/cucumber-html-reports',
                    reportFiles: 'overview-features.html',
                    reportName: 'Cucumber HTML Report'
                ])

                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
        failure {
            echo 'Build failed. Review test results and screenshots.'
        }
    }
}
