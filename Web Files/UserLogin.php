<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 $Sql_Query = "select * from user where user_email = '$email' and user_password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 $users = array(); 

//this is our sql query 
$sql = "SELECT id, first_name, last_name, user_email, role, dob, Gender, address,addRegistered FROM user where user_email = '$email';";

//creating an statment with the query
$stmt = $con->prepare($sql);

//executing that statment
$stmt->execute();

//binding results for that statment 
$stmt->bind_result($id, $first_name, $last_name, $user_email, $role, $dob, $Gender, $address, $addRegistered);

//looping through all the records
while($stmt->fetch()){
	
	//pushing fetched data in an array 
	$temp = [
		'id'=>$id,
		'fname'=>$first_name,
		'lname'=>$last_name,
		'uemail'=>$user_email,
		'role'=>$role,
		'dob'=>$dob,
		'Gender'=>$Gender,
		'address'=>$address,
		'addRegistered'=>$addRegistered,
		
	
	
	];
	
	//pushing the array inside the hero array 
	array_push($users, $temp);
}

//displaying the data in json format 
echo json_encode($users);


 
 
 }
 else{
 echo "Email not registered or password incorrect! Try again.";
 }
 
 }else{
 echo "Check Again.";
 }
mysqli_close($con);

?>