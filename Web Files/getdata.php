<?php
$servername = "localhost";
$username = "swatantranew_hoppingtomato";
$password = "Namang344@gmail";
$database = "swatantranew_hoppingtomato";

// Create connection
$conn = new mysqli($servername, $username, $password,$database);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";

$users = array(); 

//this is our sql query 
$sql = "SELECT id, first_name, last_name, user_email, role, dob, Gender, address,addRegistered FROM user;";

//creating an statment with the query
$stmt = $conn->prepare($sql);

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


?>