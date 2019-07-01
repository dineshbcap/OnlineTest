/**
 * This function is used for get the next set of questions in the pareticular
 * subject(pagination concept)
 * @param beginNumber
 * @param endNumber
 * @param numberOfQues
 * @param check
 */
function nextSlot(beginNumber,endNumber,numberOfQues,check){
	beginNumber=parseInt(endNumber)+1;
	if(parseInt(numberOfQues)>(parseInt(endNumber)+10)){
		endNumber=parseInt(endNumber)+10;
	}
	else{
		endNumber=parseInt(numberOfQues);
	}
	if(check=="writeTest"){
		var min=document.getElementById("minute").value;
		var sec=document.getElementById("second").value;
		var radioButton=null;
		for(var i=0;i<4;i++){
			if(document.getElementsByName('rb')[i].checked){
				radioButton=document.getElementsByName('rb')[i].value;
			}
		}	
		document.location.href = "CommonServlet?commonName=nextSlot&beginNumber="+beginNumber+"&endNumber="+endNumber+"&check="+check+"&minute="+min+"&second="+sec+"&rb="+radioButton;
	}
	else{
		var subjectName=document.frm.selectedSubjectName1.value;
		var organizationName=document.frm.selectedOrganizationName.value;
		document.location.href = "CommonServlet?commonName=nextSlot&subjectName="+subjectName+"&organizationName="+organizationName+"&beginNumber="+beginNumber+"&endNumber="+endNumber+"&check="+check;
	}
}
/**
 * This function is used for get the previous set of questions in the
 * pareticular subject(pagination concept)
 * @param beginNumber
 * @param endNumber
 * @param numberOfQues
 * @param check
 */
function previousSlot(beginNumber,endNumber,numberOfQues,check){
	beginNumber=parseInt(beginNumber)-10;
	endNumber=parseInt(beginNumber)+9;
	if(check=="writeTest"){
		var min=document.getElementById("minute").value;
		var sec=document.getElementById("second").value;
		var radioButton=null;
		for(var i=0;i<4;i++){
			if(document.getElementsByName('rb')[i].checked){
				radioButton=document.getElementsByName('rb')[i].value;
			}
		}	
		document.location.href = "CommonServlet?commonName=nextSlot&beginNumber="+beginNumber+"&endNumber="+endNumber+"&check="+check+"&minute="+min+"&second="+sec+"&rb="+radioButton;
	}
	else{
		var subjectName=document.frm.selectedSubjectName1.value;
		var organizationName=document.frm.selectedOrganizationName.value;
		document.location.href = "CommonServlet?commonName=nextSlot&subjectName="+subjectName+"&organizationName="+organizationName+"&beginNumber="+beginNumber+"&endNumber="+endNumber+"&check="+check;
	}
}
/**
 * This function is used for to view the next set of filtered results
 * @param beginNumber
 * @param endNumber
 * @param totalNumber
 * @param check
 */
function resultSlider(beginNumber,endNumber,totalNumber,check,test){
	var beginNumber1=0;
	var endNumber1=0;
	if(check=='previous'){
		beginNumber1=beginNumber-10;
		endNumber1=beginNumber1+10;
	}
	else if(check=='next'){
		beginNumber1=endNumber;
		if(totalNumber>(endNumber+10)){
			endNumber1=endNumber+10;
		}
		else{
			endNumber1=totalNumber;
		}
	}
	document.location.href = "CommonServlet?commonName=resultSlider&initialNumber="+beginNumber1+"&lastNumber="+endNumber1+"&test="+test;
}
/**
 * This function is used for hide the admin details other than select name.. on
 * page load. Depends on that name the details will be displayed below
 */
function hideAdmin(){
	document.frm.selectedName.disabled = false;
	document.frm.lastName.disabled = true;
    document.frm.emailId.disabled = true;
    document.frm.mobileNumber.disabled = true;
    document.frm.userName.disabled = true;
    document.frm.passWord.disabled = true;
    document.frm.rePassWord.disabled = true;
    document.frm.isAdmin.disabled = true;
    document.frm.selectedOrganizationName.disabled = true;
}
/**
 * This function is used for release the hided fields
 */
function releaseHideAdmin(){
	document.frm.lastName.disabled = false;
    document.frm.emailId.disabled = false;
    document.frm.mobileNumber.disabled = false;
    document.frm.userName.disabled = false;
    document.frm.passWord.disabled = false;
    document.frm.rePassWord.disabled = false;
    document.frm.isAdmin.disabled = false;
}
/**
 * This function is used for hide the admin or users details. when, User clicks
 * '-select-' option in the choose field
 */
function hideAdminUserDetails(){
	var checkName=document.getElementById("selectedName").value;
	if(checkName==""){
		document.frm.selectedOrganizationName.disabled = false;
		releaseHideAdmin();
	}
	else if(checkName=="-select-"){
		hideAdmin();
	}
}
/**
 * this block is used for get the number of questions in the selected subject
 * and this one goes to the doGet() method
 * @param check
 */
function getNumberOfQuestion(check){
	if(check=='AddQuestion'){
		if(selectSubject1()){
			var subjectName=document.frm.selectedSubjectName1.value;
			var organizationName=document.frm.selectedOrganizationName.value;
			document.location.href = "CommonServlet?subjectName="+subjectName+"&organizationName="+organizationName+"&commonName=getQuestionNumber&check="+check;
		}
		else{
			return false;
		}
	}
	if(check=='ModifyQuestion'){
	var subjectName=document.frm.selectedSubjectName1.value;
	var organizationName=document.frm.selectedOrganizationName.value;
	document.location.href = "CommonServlet?subjectName="+subjectName+"&organizationName="+organizationName+"&commonName=getQuestionNumber&check="+check;
	}
	else if(check=='DeleteQuestion'){
		var subjectName=document.frm.selectedSubjectName1.value;
		var organizationName=document.frm.selectedOrganizationName.value;
		document.location.href = "CommonServlet?subjectName="+subjectName+"&organizationName="+organizationName+"&commonName=getQuestionNumber&check="+check;
	}
	else if(check=='writeTest'){
		var subjectName=document.frm.selectedSubjectName1.value;
		if((subjectName=="-select-")||(subjectName=="")){
			document.getElementById('answeredError').innerHTML="Please Select The Subject!";
		 	document.getElementById('answeredError').style.color="red";
		 	return false;
		}
		else{
			document.location.href = "CommonServlet?subjectName="+subjectName+"&commonName=getQuestionNumber&check="+check;
		}
	}
}
/**
 * this block for set the number of questions and subject name in the drop down
 * @param verification
 * @param subjectName
 */
function setNumberOfQuestion(verification,subjectName,organizationName){
	if(verification=='modify'){
	document.frm.selectedSubjectName1.value=subjectName;
	document.frm.selectedOrganizationName.value=organizationName;
	}
	if(verification=='delete'){
		document.frm.selectedSubjectName1.value=subjectName;
		document.frm.selectedOrganizationName.value=organizationName;
	}
}
/**
 * this is used in delete user. while choose the type of user it get the list of
 * names
 */
function adminOrUser() {
	var userName=document.frm.typeOfUser.value;
	if(userName=="-select-"){
		document.getElementById('userTypeErr').innerHTML="select The User Type!";
	 	document.getElementById('userTypeErr').style.color="red";
	 	return false;
	}
	else{
		document.frm.userType.value=userName;
	
		document.location.href = "CommonServlet?userName="+userName+"&commonName=selectUser";
	}
} 
/**
 * set the user name in the drop down
 * @param name
 */
function setUserNameFor(name){
	if(name=='hai'){
	alert("Can not possible to delete current Admin!!");
	}
	else{
	document.frm.typeOfUser.value = name;
	}
}

/**
 * this is for get organization name for the selected subject name
 * @param test
 */
function getOrganizationName(test){
	var subjectName=document.frm.selectedSubjectName.value;
	document.location.href = "CommonServlet?subjectName="+subjectName+"&commonName=getOrganizationName&test="+test;
}
/**
 * this is for set the organization name
 * @param subjectName
 * @param organizationName
 */
function setOrganizationName(subjectName,organizationName){
	document.frm.selectedSubjectName.value=subjectName;
	if(organizationName==null){
		document.frm.selectedOrganizationName.value='-select-';
	}
	else{
		document.frm.selectedOrganizationName.value=organizationName;
	}
}
/**
 * this is for get the detail for the selected user
 * @param check
 */
function getDetailOfAdminOrUser(check) {
	var name=document.getElementById("selectedName").value;
	alert(name);
	if(name=="-select-"){
		hideAdmin();
	}
	else{
		if(check=='admin'){
			document.location.href = "CommonServlet?userName="+name+"&commonName=getDetailAdminOrUser&check=admin";
		}
		else if(check=='user'){
		
			document.location.href = "CommonServlet?userName="+name+"&commonName=getDetailAdminOrUser&check=user";
		}
	}
} 
/**
 * this is for set the geted user details
 * @param name
 * @param check
 * @param i
 */
function setAdminOrUser(name,check,i){
	document.getElementById("selectedName").value = name;
	if(check=='releaseHideAdmin'){
	     document.frm.selectedOrganizationName.disabled = true;
	     document.getElementById("isAdmin").checked=true;
	     document.frm.isAdmin.disabled = false;
	     releaseHideAdmin();
	}
	if(check=='releaseHideUser'){
		document.frm.selectedOrganizationName.disabled = false;
		releaseHideAdmin();
		document.getElementById("selectedOrganizationName").value =i;
	}
}
/**
 * this block for set the name
 */
function setNameAndAdminOrUser1(name,name1){
	visibleAdminUserDetails();
	document.getElementById("selectedName").value = name;
	document.getElementById("selectedName1").value = name1;
}
/**
 * this block for visible/disable the organization list for depends the check in
 * admin box
 */
function hideSelect()
{
	if (document.frm.isAdmin.checked == true){
		document.getElementById('orgMsg').innerHTML="";
		document.frm.selectedOrganizationName.disabled = true;
	}
	else{
		document.frm.selectedOrganizationName.disabled = false;
	}
	
}
/**
 * this is for get the question and options for the selected button
 * @param i
 * @param test
 */
function call(i,test,beginNumber,endNumber){
	var subjectName=document.frm.selectedSubjectName1.value;
	var organizationName=document.frm.selectedOrganizationName.value;
	document.frm.selectedSubjectNameInAdmin.value=subjectName;
	document.location.href = "CommonServlet?questionNumber="+i+"&commonName=getQuestionAndAnswer&subjectName="+subjectName+"&check="+test+"&organizationName="+organizationName+"&beginNumber="+beginNumber+"&endNumber="+endNumber;
}
/**
 * this is for logout and move to login page
 */
function logout(){
	document.location.href = "CommonServlet?commonName=logout";
}
/**
 * This function is used for move the button click action to newuser.jsp page
 */
function newUser(){
	document.location.href = "CommonServlet?commonName=newUser";
}
/**
 * this is for get the list of admins
 */
function getUserAdminList(){
	document.location.href = "CommonServlet?commonName=getUserAdminList";
}
/**
 * this for set the subject names
 * @param subjectName
 */
function subjectNameToSet(subjectName,msg){
	document.frm.selectedSubjectName1.value=subjectName;
		if(msg=='addQuestion'){
			document.getElementById('addQuestionSuccess').innerHTML="Question Added Successfully";
		 	document.getElementById('addQuestionSuccess').style.color="Green";
		}
		else if(msg=='modifyQuestion'){
			document.getElementById('modifyQuestionSuccess').innerHTML="Question modified Successfully";
		 	document.getElementById('modifyQuestionSuccess').style.color="Green";
		}
		else if(msg=='deleteQuestion'){
			document.getElementById('deleteQuestionSuccess').innerHTML="Question deleted Successfully";
		 	document.getElementById('deleteQuestionSuccess').style.color="Green";
		}
		document.frm.selectedSubjectNameInAdmin.value=subjectName;
}
/**
 * questions and options stored in session. and here we set the subject name and
 * correct checked answers
 * @param subjectName
 * @param check1
 * @param check2
 * @param check3
 * @param check4
 */
function subjectNameAndAnswer(subjectName,organizationName,check1,check2,check3,check4,beginNumber,endNumber){
	document.frm.selectedOrganizationName.value=organizationName;
	document.frm.selectedSubjectName1.value=subjectName;
		if(check1==1){
			document.getElementById("cb0").checked=true;
		}
		if(check2==1){
			document.getElementById("cb1").checked=true;
		}
		if(check3==1){
			document.getElementById("cb2").checked=true;
		}
		if(check4==1){
			document.getElementById("cb3").checked=true;
		}
}
/**
 * this is for find the user already answered/question present or not for this
 * subject
 * @param name
 * @param subjectName
 */
function userAnswered(name,subjectName){
	document.getElementById('selectedSubjectName1').value=subjectName;
	if(name=='answered'){
		document.getElementById('answeredError').innerHTML="YOU ALREADY ANSWERED CHOOSE SOME OTHER SUBJECT";
	 	document.getElementById('answeredError').style.color="red";
	}
	else if(name=='noQuestion'){
		document.getElementById('answeredError').innerHTML="SORRY! THERE IS NO QUESTION IN THIS SUBJECT";
	 	document.getElementById('answeredError').style.color="red";
	}
	else{
		document.getElementById('answeredError').innerHTML="";
	}
}
/**
 * this is for display the user mark
 * @param mark
 * @param test
 */
function userMark(mark,test){
	if(test=='test'){
		var display="YOUR MARK IS: "+mark;
		document.getElementById('userMark').innerHTML=display;
	 	document.getElementById('userMark').style.color="green";
	}
	else{
		document.getElementById('userMark').innerHTML="";
	}
}
/**
 * this is for check the user answered option in that relavant questions
 * @param check1
 * @param check2
 * @param check3
 * @param check4
 */
function checkBox(check1,check2,check3,check4,questionNumber,totQuestion,beginNumber,endNumber){
	document.getElementById("NormalButton1").disabled=false;
	document.getElementById("NormalButton2").disabled=false;
	if(questionNumber==beginNumber){
		document.getElementById("NormalButton1").disabled=true;
	}
	if(questionNumber==endNumber){
		document.getElementById("NormalButton2").disabled=true;
	}
	
	if(check1==1){
		document.getElementById("cb0").checked=true;
		
	}
	if(check2==1){
		document.getElementById("cb1").checked=true;
	}
	if(check3==1){
		document.getElementById("cb2").checked=true;
		
	}
	if(check4==1){
		document.getElementById("cb3").checked=true;
		
	}
}
/**
 * check the user name
 */
function checkName(){
	var checkName=document.getElementById("selectedName").value;
	var alphabet = /^[a-zA-Z]*$/;
			if(!checkName.match(alphabet)){
				document.getElementById('namError').innerHTML="alphabet only";
			 	document.getElementById('namError').style.color="red";
			 	return false;
			}
			else if((checkName.length>=0)&&(checkName.length<5)){
				document.getElementById('namError').innerHTML="name minimum 5 letter";
			 	document.getElementById('namError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('namError').innerHTML="";
			}
}
function checkLastName(){
	var checkName=document.getElementById("lastName").value;
	var alphabet = /^[a-zA-Z]*$/;
			if(checkName==""){
				document.getElementById('lastNameError').innerHTML="Please Enter The Name";
				document.getElementById('lastNameError').style.color="red";
				return false;
			}
			else if(!checkName.match(alphabet)){
				document.getElementById('lastNameError').innerHTML="alphabet only";
			 	document.getElementById('lastNameError').style.color="red";
			 	return false;
			}
			else if((checkName.length>=0)&&(checkName.length<5)){
				document.getElementById('lastNameError').innerHTML="name minimum 5 letter";
			 	document.getElementById('lastNameError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('lastNameError').innerHTML="";
			}
}
/**
 * check the mobile number
 */
function checkMobileNumber(){
	var mobileNumber=document.getElementById("mobileNumber").value;
	if((mobileNumber==null)||(mobileNumber=="")){
		document.getElementById('mobileNumberError').innerHTML="Please Enter The Mobile Number!";
	 	document.getElementById('mobileNumberError').style.color="red";
	 	return false;
	}
	else if(mobileNumber.charAt(0)<=7){
		document.getElementById('mobileNumberError').innerHTML="numbers not exsist";
	 	document.getElementById('mobileNumberError').style.color="red";
	 	return false;
	}
	else if(isNaN(mobileNumber)){
		document.getElementById('mobileNumberError').innerHTML="numbers only";
	 	document.getElementById('mobileNumberError').style.color="red";
	 	return false;
	}
	else if((mobileNumber.length>=0)&&(mobileNumber.length<10)){
		document.getElementById('mobileNumberError').innerHTML="mobile number must be 10 digit";
		document.getElementById('mobileNumberError').style.color="red";
		return false;
	}
	else{
		document.getElementById('mobileNumberError').innerHTML="";
	}
}
/**
 * check the mail id
 */
function checkEmailId(){
	var mailId=document.getElementById("emailId").value;
	var regularExpression = /^[A-Za-z](([_\.]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/;
	if(mailId.length==0){
		document.getElementById('mailIdError').innerHTML="enter your mailId";
		document.getElementById('mailIdError').style.color="red";
		return false;
	}
	else if(!mailId.match(regularExpression)){
		document.getElementById('mailIdError').innerHTML="enter valid mailId";
	 	document.getElementById('mailIdError').style.color="red";
	 	return false;
	}
	else{
		document.getElementById('mailIdError').innerHTML="";
	}
}
/**
 * check the user name
 */
function checkUserName(){
	var userName=document.getElementById("userName").value;
	var alphabet = /^[a-zA-Z]*$/;
			if(!userName.match(alphabet)){
				document.getElementById('userNameError').innerHTML="alphabet only";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else if((userName.length>=0)&&(userName.length<3)){
				document.getElementById('userNameError').innerHTML="Name minimum 3 letter";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('userNameError').innerHTML="";
			}
}
/**
 * check the user name
 */
function checkUserName1(){
	var userName=document.getElementById("userName").value;
	var checkUsername = /^[A-Za-z0-9_]*$/;
			if(!userName.match(checkUsername)){
				document.getElementById('userNameError').innerHTML="alpha numeric and underscore(_) only";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else if((userName.length>=0)&&(userName.length<3)){
				document.getElementById('userNameError').innerHTML="Name minimum 3 letter";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('userNameError').innerHTML="";
			}
}
/**
 * check the Subject name
 */
function checkSubjectName(){
	var subjectName=document.getElementById("userName").value;
   if((subjectName.length>=0)&&(subjectName.length<=2)){
		document.getElementById('userNameError').innerHTML="Name minimum 3 letter";
	 	document.getElementById('userNameError').style.color="red";
	 	return false;
	}
	else{
		document.getElementById('userNameError').innerHTML="";
	}
}
/**
 * check the organization name
 */
function checkOrganizationName(){
	var userName=document.getElementById("userName").value;
	var alphabet = /^[a-zA-Z0-9_]*$/;
			if(userName==""){
				document.getElementById('userNameError').innerHTML="Please enter the new name for organization";
				document.getElementById('userNameError').style.color="red";
				return false;
			}		
			else if(!userName.match(alphabet)){
				document.getElementById('userNameError').innerHTML="alphabet numeric only";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else if((userName.length>=0)&&(userName.length<=2)){
				document.getElementById('userNameError').innerHTML="Name minimum 3 letter";
			 	document.getElementById('userNameError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('userNameError').innerHTML="";
			}
}
/**
 * check the password
 */
function checkPassWord(){
	var passWord=document.getElementById("passWord").value;
	var alphaNumeric = /^[a-zA-Z0-9_]*$/;
			if(!passWord.match(alphaNumeric)){
				document.getElementById('passWordError').innerHTML="alphabet or numeric only";
			 	document.getElementById('passWordError').style.color="red";
			 	return false;
			}
			else if((passWord.length>=0)&&(passWord.length<5)){
				document.getElementById('passWordError').innerHTML="passWord minimum 5 letter";
			 	document.getElementById('passWordError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('passWordError').innerHTML="";
			}
}
/**
 * check the password match with re entered one
 */
function checkRePassWord(){
	var passWord=document.getElementById("rePassWord").value;
	var oldPassWord=document.getElementById("passWord").value;
	var alphaNumeric = /^[a-zA-Z0-9_]*$/;
			if(passWord!=oldPassWord){
				document.getElementById('rePassWordError').innerHTML="password does not match!!";
			 	document.getElementById('rePassWordError').style.color="red";
			 	return false;
			}
			else if(!passWord.match(alphaNumeric)){
				document.getElementById('rePassWordError').innerHTML="alphabet or numeric only";
			 	document.getElementById('rePassWordError').style.color="red";
			 	return false;
			}
			else if((passWord.length>=0)&&(passWord.length<5)){
				document.getElementById('rePassWordError').innerHTML="passWord minimum 5 letter";
			 	document.getElementById('rePassWordError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('rePassWordError').innerHTML="";
			}
}
function checkOrganizationName1(){
	if(document.getElementById("selectedOrganizationName").value=="-select-"){
		document.getElementById('orgMsg').innerHTML="Please select the organization";
		document.getElementById('orgMsg').style.color="red";
		return false;
	}
	else{
		document.getElementById('orgMsg').innerHTML="";
		return true;
	}
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function validateUserDetails(){
	var i=0;
	var checkName=document.getElementById("selectedName").value;
			if(checkName==""){
				document.getElementById('namError').innerHTML="Please Enter The Name";
				document.getElementById('namError').style.color="red";
				i++;
				return false;
			}
			else if(checkName=="-select-"){
				document.getElementById('namError').innerHTML="Please Choose The Name To See Details!";
			 	document.getElementById('namError').style.color="red";
			 	return false;
			}
			else{
				document.getElementById('namError').innerHTML="";
			 	
			}
	if(checkLastName()==false){
		i++;
	}
	if(checkMobileNumber()==false){
		i++;
	}
	if(checkEmailId()==false){
		i++;
	}
	if(checkUserName1()==false){
		i++;
	}
	if(checkPassWord()==false){
		i++;
	}
	if(checkRePassWord()==false){
		i++;
	}
	if (document.frm.isAdmin.checked == false){
		if(checkOrganizationName1()==false){
			i++;
		}
	}
	if(i>0){
		return false;
	}
	return true;
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function validateUserDetails1(){
	var i=0;
	if(checkName()==false){
		i++;
	}
	if(checkMobileNumber()==false){
		i++;
	}
	if(checkEmailId()==false){
		i++;
	}
	if(checkUserName1()==false){
		i++;
	}
	if(checkPassWord()==false){
		i++;
	}
	if(checkRePassWord()==false){
		i++;
	}
	if(checkOrganizationName1()==false){
		i++;
	}
	if(i>0){
		return false;
	}
	return true;
}
function isNullOrEmpty(test) {
	count=0;
	var alphaNumeric = /^[a-zA-Z0-9_]*$/;
    for(var i=0;i<test.length;i++){
    	if(test.charAt(i).match(alphaNumeric)){
    		return false;
    	}
    }
   return true;
}
/**
 * This function is used for check the question and answers typed properly or
 * not, before submiting
 * @returns {Boolean}
 */
function checkQuestion(){
	var adminQuestion=document.frm.adminQuestion.value;
	var count=0;
	var option1=document.frm.option1.value;
	var option2=document.frm.option2.value;
	var option3=document.frm.option3.value;
	var option4=document.frm.option4.value;
	for(var i=0;i<4;i++){
		if((document.getElementById("cb"+i).checked)==true){
			count++;
		}
	}
	if(isNullOrEmpty(adminQuestion)){
		alert("ADD PROPER QUESTION");
		return false;
	}
	else if((adminQuestion.length==0)||(adminQuestion.length>1000)){
		alert("QUESTION NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option1.length==0)||(option1.length>1000)){
		alert("OPTION1 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option2.length==0)||(option2.length>1000)){
		alert("OPTION2 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option3.length==0)||(option3.length>1000)){
		alert("OPTION3 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option4.length==0)||(option4.length>1000)){
		alert("OPTION4 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if(count==0){
		alert("must select one radio button");
		return false;
	}
	return true;
}
/**
 * This function is used for check the question and answers typed properly or
 * not, before submiting
 * @returns {Boolean}
 */
function checkQuestion1(){
	var organizationName=document.getElementById("selectedOrganizationName").value;
	var subjectName=document.getElementById("selectedSubjectName1").value;
	var question=document.frm.question.value;
	var count=0;
	var option1=document.frm.option1.value;
	var option2=document.frm.option2.value;
	var option3=document.frm.option3.value;
	var option4=document.frm.option4.value;
	for(var i=0;i<4;i++){
		if((document.getElementById("cb"+i).checked)==true){
			count++;
		}
	}
	if((organizationName=="-select-")||(organizationName=="")){
		document.getElementById('orgMsg').innerHTML="Select The Organization";
	 	document.getElementById('orgMsg').style.color="Red";
	 	return false;
	}
	else if((subjectName=="-Subject Name-")||(subjectName=="")){
		document.getElementById('subMsg').innerHTML="Select The Subject Name";
	 	document.getElementById('subMsg').style.color="Red";
	 	return false;
	}
	else if(isNullOrEmpty(question)){
		alert("ADD PROPER QUESTION");
		return false;
	}
	else if((question.length==0)||(question.length>1000)){
		alert("QUESTION NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option1.length==0)||(option1.length>1000)){
		alert("OPTION1 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option2.length==0)||(option2.length>1000)){
		alert("OPTION2 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option3.length==0)||(option3.length>1000)){
		alert("OPTION3 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if((option4.length==0)||(option4.length>1000)){
		alert("OPTION4 NOT NULL OR GREATER THAN 1000");
		return false;
	}
	else if(count==0){
		alert("CHECK ATLEAST ONE CHECK BOX");
		return false;
	}
	return true;
}
/**
 * This function is used for set the msg after user modified
 * @param msg
 * @param dummy
 */
function setaddUserSuccessMessage(msg,dummy){
	if(msg=='modifyUser'){
		document.getElementById('modifyUserSuccess').innerHTML="User modified Successfully";
	 	document.getElementById('modifyUserSuccess').style.color="Green";
	}
}
/**
 * This function is used for set the organization message
 * @param msg
 * @param dummy
 */
function organizationSuccessMessage(msg,dummy){
	if(msg=='addOrganization'){
		document.getElementById('addOrganizationSuccess').innerHTML="Organization Added Successfully";
	 	document.getElementById('addOrganizationSuccess').style.color="Green";
	}
	else if(msg=='modifyOrganization'){
		document.getElementById('modifyOrganizationSuccess').innerHTML="Organization modified Successfully";
	 	document.getElementById('modifyOrganizationSuccess').style.color="Green";
	}
	else if(msg=='deleteOrganization'){
		document.getElementById('deleteOrganizationSuccess').innerHTML="Organization deleted Successfully";
	 	document.getElementById('deleteOrganizationSuccess').style.color="Green";
	}
}
/**
 * This function is used for confirm the user before delete
 * @returns {Boolean}
 */
function confirmation(){
	var boolean=confirm("Are you sure to delete?");
	if(!boolean){
		return false;
	}
}
/**
 * This function is used for confirm the user before press some other link
 * @returns {Boolean}
 */
function confirmDataLoss(){
	var boolean=confirm("Are you sure? The data will be loss!!");
	if(!boolean){
		return false;
	}
}
function confirmationSubmit(){
	var minute=document.getElementById("minute").value;
	var second=document.getElementById("second").value;
		if((minute==0)&&(second==0)){
			boolean=true;
		}
	else{	
		question=document.getElementById("question").value;
		option1=document.getElementById("option1").value;
		option2=document.getElementById("option2").value;
		option3=document.getElementById("option3").value;
		option4=document.getElementById("option4").value;
		document.getElementById("question").value="";
		document.getElementById("option1").value="";
		document.getElementById("option2").value="";
		document.getElementById("option3").value="";
		document.getElementById("option4").value="";
		boolean=confirm("Are you want to complete the test?");
	}
	if(!boolean){
		document.getElementById("question").value=question;
		document.getElementById("option1").value=option1;
		document.getElementById("option2").value=option2;
		document.getElementById("option3").value=option3;
		document.getElementById("option4").value=option4;
		return false;
	}
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function confirmationInDeleteSubject(){
	var count=0;
	if(selectOrganization()==false){
		count++;
	}
	if(selectSubject()==false){
		count++;
	}
	if(count==0){
		var boolean=confirm("Are you sure to delete?");
		if(!boolean){
			return false;
		}
		else{
			return true;
		}
	}
	else{
		return false;
	}
}
/**
 * This function is used for get the list of subjects
 * @param check
 */
function getListOfSubject(check){
	if(check=="FilterResult"){
		organizationName=document.getElementById("selectedOrganizationName1").value;
	}
	else{
		organizationName=document.getElementById("selectedOrganizationName").value;
	}
	document.location.href = "CommonServlet?commonName=getSubjectList&organizationName="+organizationName+"&check="+check;
}
/**
 * This function is used for set the subject name
 * @param organizationName
 */
function setSubjectName(organizationName){
	if(!(organizationName=="-select-")||(organizationName=="")){
		document.frm.selectedOrganizationName.value=organizationName;
	}
}
function setSubjectName1(organizationName,subjectName){
	if(!((organizationName=="-select-")||(organizationName==""))){
		document.frm.selectedOrganizationName.value=organizationName;
		document.frm.selectedSubjectName1.value=subjectName;
		document.frm.SubjectName.disabled =false;
	}
	else{
		document.frm.SubjectName.disabled =true;
	}
}
function test(){
	if(document.frm.selectedOrganizationName.value=="-select-"){
		document.frm.selectedSubjectName1.disabled = true;
	}
	else{
		document.frm.selectedSubjectName1.disabled = false;
	}
}
/**
 * This function is used for confirm the admin before deleteing organization
 * @param beginNumber
 * @param endNumber
 * @returns
 */
function verifyDelete(){
	var organizationName=document.getElementById("selectedOrganizationName").value;
	var subjectName=document.getElementById("selectedSubjectName1").value;
	if((organizationName=="-select-")||(organizationName=="")){
		document.getElementById('orgMsg').innerHTML="Select The Organization";
	 	document.getElementById('orgMsg').style.color="Red";
	 	return false;
	}
	else if((subjectName=="-Subject Name-")||(subjectName=="")){
		document.getElementById('subMsg').innerHTML="Select The Subject Name";
	 	document.getElementById('subMsg').style.color="Red";
	 	return false;
	}
	else{
		beginNumber=document.getElementById("beginNum").value;
		endNumber=document.getElementById("endNum").value;
		var count=0;
		for(var i=beginNumber;i<=endNumber;i++){
			if((document.getElementById("goTo["+i+"]").checked)==true){
				count++;
				return confirmation();
			}
		}
		if(count==0){
			alert("Please Select The Checkbox To Delete");
			return false;
		}
	}
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function modifySubject(){
	var count=0;
	if(selectOrganization()==false){
		count++;
	}
	if(selectSubject()==false){
		count++;
	}
	if(checkSubjectName()==false){
		count++;
	}
	if(count==0){
		return true;
	}
	else{
		return false;
	}
}
function selectSubject(){
	var subjectName=document.getElementById("SubjectName").value;
	if((subjectName=="-select-")||(subjectName=="")){
		document.getElementById('subjectError').innerHTML="Select The Subject";
	 	document.getElementById('subjectError').style.color="Red";
		return false;
	}
	else{
		document.getElementById('subjectError').innerHTML="";
		return true;
	}
}
function selectSubject1(){
	var subjectName=document.getElementById("selectedSubjectName1").value;
	if((subjectName=="-select-")||(subjectName=="")){
		document.getElementById('subjectError').innerHTML="Select The Subject";
	 	document.getElementById('subjectError').style.color="Red";
		return false;
	}
	else{
		document.getElementById('subjectError').innerHTML="";
		return true;
	}
}
function selectOrganization(){
	var organizationName=document.getElementById("selectedOrganizationName").value;
	if((organizationName=="-select-")||(organizationName=="")){
		document.getElementById('orgMsg').innerHTML="Select The Organization";
	 	document.getElementById('orgMsg').style.color="Red";
		return false;
	}
	else{
		document.getElementById('orgMsg').innerHTML="";
		return true;
	}
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function addSubject(){
	var count=0;
	if(selectOrganization()==false){
		count++;
	}
	if(checkSubjectName()==false){
		count++;
	}
	if(count==0){
		return true;
	}
	else{
		return false;
	}
}
function removeAddSubError(){
	document.getElementById('userNameError').innerHTML="";
	document.getElementById('orgMsg').innerHTML="";
}
function infoToSelect(){
	document.getElementById('modifyQuestionSuccess').innerHTML="";
	var question=document.getElementById("question").value;
	var option1=document.getElementById("option1").value;
	var option2=document.getElementById("option2").value;
	var option3=document.getElementById("option3").value;
	var option4=document.getElementById("option4").value;
	if(((question)||(option1)||(option2)||(option3)||(option4))==""){
		alert("Please Select The Question To Modify!!");
		document.frm.question.disabled = true;
		document.frm.option1.disabled = true;
		document.frm.option2.disabled = true;
		document.frm.option3.disabled = true;
		document.frm.option4.disabled = true;
		document.frm.cb0.disabled = true;
		document.frm.cb1.disabled = true;
		document.frm.cb2.disabled = true;
		document.frm.cb3.disabled = true;
		document.frm.MediumButton.disabled = true;
	}
	return false;
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function addQuestionVerify(){
	var count=0;
	if(selectOrganization()==false){
		count++;
	}
	if(selectSubject1()==false){
		count++;
	}
	if(count==0){
		return true;
	}
	else{
		return false;
	}
}
/**
 * This function is used for confirm the organization delete
 * @returns
 */
function confirmationOrgDelete(){
	if(selectOrganization()==false){
		return false;
	}
	else{
		return confirmation();
	}
}
/**
 * This function is used for check some other functions and depends on that
 * return the boolean value
 * @returns {Boolean}
 */
function modifyOrgCheck(){
	var count=0;
	if(selectOrganization()==false){
		count++;
	}
	if(checkOrganizationName()==false){
		count++;
	}
	if(count==0){
		return true;
	}
	else{
		return false;
	}
}
/**
 * This function is used for check the mark filter field
 * @returns {Boolean}
 */
function checkMark(){
	if(isNaN(document.frm.MarkRange.value)){
		document.getElementById('markRangeError').innerHTML="numbers only";
	 	document.getElementById('markRangeError').style.color="red";
	 	return false;
	}
	else{
		document.getElementById('markRangeError').innerHTML="";
	}
}
/**
 * This function is used for confirm the users delete
 * @returns true or false
 */
function confirmationUserDelete(){
	count=0;
	var name=document.getElementById('deleteName').value;
	var userName=document.frm.typeOfUser.value;
	if(userName=="-select-"){
		document.getElementById('userTypeErr').innerHTML="select The User Type!";
	 	document.getElementById('userTypeErr').style.color="red";
		count++;
	}
	if(name=="-name-"){
		document.getElementById('nameErr').innerHTML="After selecting User Type Select Name!";
	 	document.getElementById('nameErr').style.color="red";
	 	count++;
	}
	if(count==0){
		return confirmation();
	}
	else{
		return false;
	}
}
/**
 * This function is used for set the subject and organization name
 * @param organizationName
 * @param subjectName
 */
function setSubjectAndOrg(organizationName,subjectName){
	document.getElementById('selectedOrganizationName').value=organizationName;
	document.getElementById('SubjectName').value=subjectName;
}
/**
 * This function is used for remove the particular value from browser's URL
 * @param nameToRemove
 */
function removeVariableFromURL(nameToRemove) {
	var URLNameToRemove=nameToRemove;
    var URL = String(top.location.href);
    var regex = new RegExp( "\\?" + URLNameToRemove + "=[^&]*&?", "gi");
    URL = URL.replace(regex,'?');
    regex = new RegExp( "\\&" + URLNameToRemove + "=[^&]*&?", "gi");
    URL = URL.replace(regex,'&');
    URL = URL.replace(/(\?|&)$/,'');
    regex = null;
    top.location.href = URL;
}
