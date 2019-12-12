pipeline{

agent any
triggers {

        pollSCM ('0 18 * * *')
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
 body: """
<p style=\"color:#006600;\">Hi All, <br>
This is a confirmation mail that all <b><i>ZestIOT automation scripts </b></i>are successfully executed through Jenkins Pipeline.<br>
<b>Job Bilid Number: </b>'${env.BUILD_NUMBER}'<br>
<b>Job Name:</b>'${env.JOB_NAME}'<br>
Check <b>console output</b> at <b><i><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></b></i><br><br>
Thanks& Reagrds<br>
Automation Team.</p>
""", 
cc: 'pdwadasi@enhops.com', 
from: 'automationteam.enhops@gmail.com', 
mimeType: 'text/html', 
replyTo: '',
 subject: "BUILD_NUMBER '${env.BUILD_NUMBER}' SUCCESSFUL : Jenkins Pipeline " ,
 to: 'stiyyagura@enhops.com'
}
}
}
}