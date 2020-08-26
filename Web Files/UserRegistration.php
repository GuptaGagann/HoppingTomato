<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $F_name = $_POST['f_name'];
 $L_name = $_POST['L_name'];
 $email = $_POST['email'];
 $password = $_POST['password'];
 $Role=$_POST['role'];
 $Gender=$_POST['gender'];
 $Date=$_POST['date'];
 $CheckSQL = "SELECT * FROM user WHERE user_email='$email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Email Already Exist';

 }
else{ 
$Sql_Query = "INSERT INTO user (first_name,last_name,user_email,user_password,role,Gender,dob) values ('$F_name','$L_name','$email','$password','$Role','$Gender','$Date')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'Registration Successfully';
}
else
{
 echo 'Something went wrong';
 }
 }
}
 mysqli_close($con);
?>