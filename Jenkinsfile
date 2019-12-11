pipeline{

agent any
triggers {
        cron('0 12 * * *')
    }
stages 
{
stage('Clone') 
{
steps{
echo "Clone the Code from git hub.........."
checkout([$class: 'GitSCM', 
branches: [[name: '*/master']], 
doGenerateSubmoduleConfigurations: false, 
extensions: [],
 submoduleCfg: [],
  userRemoteConfigs: [[credentialsId: 'baa4c5c3-ffe4-4edb-aa94-1e37dadb520f', 
  url: 'https://github.com/RadhikaChiluka/ZestIOTAutomation.git']]])
}
}
stage('Test') 
{
steps{
echo "Running the test cases.........."
bat "mvn clean install test"
}
}

stage('Report') 
{
steps{
echo " woooooooooooooooow Deploying the Project.........."
publishHTML([allowMissing: false,
 alwaysLinkToLastBuild: false,
  keepAll: false, reportDir: '',
   reportFiles: 'ExecutionReports/HtmlReport/TestReport.html',
    reportName: 'Extent HTML Report', reportTitles: ''])
 mail bcc: '', 
 body: """This is a confirmation mail that all ZestIOT automation scripts are successfully executed through Jenkins Pipeline, Check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME}""", 
 cc:  "stiyyagura@enhops.com, nishanth@zestiot.io"  , 
 from: "AutomationTeam@Enhops",
  replyTo: '', 
  subject: "Notification:Build SUCCESSFUL '${env.JOB_NAME} ${env.BUILD_NUMBER}'" ,
   to: "stiyyagura@enhops.com"   

}
}
}
}