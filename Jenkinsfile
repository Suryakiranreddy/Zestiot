pipeline{

agent any
triggers {

        pollSCM ('0 12 * * *')
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
mail bcc: 'stiyyagura@enhops.com', body: '''Hi All,<br>
<b>body</b><br>
<h1>hiiiii</h1><br>
thanks''', cc: 'stiyyagura@enhops.com', from: 'automationteam.enhops@gmail.com', mimeType: 'text/html', replyTo: '', subject: 'notification', to: 'stiyyagura@enhops.com'
}
}
}
}