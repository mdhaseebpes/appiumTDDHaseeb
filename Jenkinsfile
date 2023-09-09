pipeline{
    
  agent {
      
    }
    
    stages{
    
        stage("Build Start Notification"){
            steps{
                echo("sending notification to slack about build details")
            }
        }

         stage("Git Checkout"){
            steps{
                echo("cloning git repo and check out")
            }
        }



        stage("Execution automation tests"){
            steps{
                echo("execute automation test in cloud device farm - BrowserStack")
            }
        }

        stage("Publish HTML Reports"){
                    steps{
                        echo("generating html reports")
                    }
                }


        stage("Publish Cucumber Reports"){
            steps{
                echo("generating cucumber reports")
            }
        }

        stage("Send report to slack"){
            steps{
                echo("send reports and build details to slack")
            }
        }

           stage("Create zip and upload to s3"){
                            steps{
                                echo("upload all reports to AWS s3 bucket")
                            }
                        }
        
    }

}
