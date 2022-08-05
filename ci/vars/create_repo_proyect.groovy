def call(company){

    writeFile([file:"$WORKSPACE/create-repo.sh",text:libraryResource("sh/create-repo.sh")])
    withCredentials([usernamePassword(credentialsId: 'murmusecret', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh "chmod +x create-repo.sh && ./create-repo.sh $company"
    }
}