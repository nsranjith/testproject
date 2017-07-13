<?php
require 'DB_Connect.php';
$name=$_POST["Name"];
$email=$_POST["Email"];
$password=md5($_POST["Password"]);
$age=$_POST["Age"];
$experience=$_POST["Experience"];
$phone=$_POST["Mobile"];
$domain=$_POST["Domain"];
$fees=$_POST["Fees"];
$book=$_POST["Booked"];
$query="INSERT INTO tutor(Name,Email,Password,Age,Experience,Mobile,Domain,Fees,Booked)VALUES('$name','$email','$password','$age','$experience','$phone','$domain','$fees','$book')";
$result=mysqli_query($conn, $query) or die("Error" .mysqli_error($conn));



?>