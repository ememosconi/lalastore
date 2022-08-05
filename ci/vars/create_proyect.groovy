def call(pack,companyName,automatic){


    writeFile([file:"$env.WORKSPACE/create-ms-java-project.sh",text:libraryResource("sh/create-ms-java-project.sh")])
    withCredentials([usernamePassword(credentialsId: 'murmusecret', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh "chmod +x create-ms-java-project.sh && ./create-ms-java-project.sh $pack $companyName $automatic"
    }
}