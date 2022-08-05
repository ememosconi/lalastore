def call(companyName){


    writeFile([file:"$env.WORKSPACE/clean-workspace.sh",text:libraryResource("sh/clean-workspace.sh")])
    withCredentials([usernamePassword(credentialsId: 'murmusecret', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh "chmod +x clean-workspace.sh && ./clean-workspace.sh $companyName"
    }
}