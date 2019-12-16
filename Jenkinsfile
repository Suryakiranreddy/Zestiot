pipeline{
agent any
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
Job Build Number:-<b>'${env.BUILD_NUMBER}'</b><br>
Job Name:-<b>'${env.JOB_NAME}'</b><br>
Check console output at <b><i><a href="${env.BUILD_URL}">${env.BUILD_URL}</a></i></b><br><br>
Thanks & Regards,<br>
Automation Team</p>
""", 
cc: 'amit@zestiot.io, sushanto@zestiot.io, sudhir@zestiot.io, Krishna@zestiot.io, anantwar@zestiot.io, shrikant@zestiot.io, aman@zestiot.io, rohan@zestiot.io, leadership@enhops.com, chiranjeevi@zestiot.io, stiyyagura@enhops.com, pdwadasi@enhops.com, rbuddha@enhops.com, rchiluka@enhops.com, smunnangi@enhops.com, nishanth@zestiot.io, hmanthena@enhops.com, mpyla@enhops.com', 
from: 'automationteam.enhops@gmail.com', 
mimeType: 'text/html', 
replyTo: '',
 subject: "BUILD_NUMBER '${env.BUILD_NUMBER}' SUCCESSFUL : Jenkins Pipeline " ,
 to: 'nilesh@zestiot.io'
}
}
}
}