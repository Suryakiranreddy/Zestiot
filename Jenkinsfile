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
 mail bcc: '', body: 'This is a confirmation mail that all the automation scripts are successfully executed through Jenkins Pipeline', cc: 'amit@zestiot.io, sushanto@zestiot.io, sudhir@zestiot.io, Krishna@zestiot.io, anantwar@zestiot.io, shrikant@zestiot.io, aman@zestiot.io, rohan@zestiot.io, leadership@enhops.com, chiranjeevi@zestiot.io, stiyyagura@enhops.com, pdwadasi@enhops.com, rbuddha@enhops.com, rchiluka@enhops.com, smunnangi@enhops.com, nishanth@zestiot.io, hmanthena@enhops.com', from: 'AutomationTeam@Enhops', replyTo: '', subject: 'Notification:Build SUCCESSFULL in Jenkins pipeline: ZestIot-Github', to: 'nilesh@zestiot.io'   

}
}
}
}