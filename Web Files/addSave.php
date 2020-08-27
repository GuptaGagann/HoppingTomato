<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

	include 'DatabaseConfig.php';
	
	$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
	
	$Email=$_POST['emaill'];
	$Address=$_POST['addr'];
	$AddressCheck=$_POST['addreg'];
	$CheckSQL = "SELECT * FROM user WHERE user_email='$Email'";
	$check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
	
 	if(isset($check)){
 		$Sql_Query= "UPDATE user SET address ='$Address',addRegistered='$AddressCheck' WHERE user_email='$Email'";
		if(mysqli_query($con,$Sql_Query)){
			$check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
			
			
		}
		else{
		 	echo 'Something went wrong';
		}
	}

	// Check connection
	if ($con->connect_error) {
		die("Connection failed: " . $con->connect_error);
	}
}
?>