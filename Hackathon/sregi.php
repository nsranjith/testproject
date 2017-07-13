<?php
require 'DB_Connect.php';
$name=$_POST["Name"];
$email=$_POST["Email"];
$password=md5($_POST["Password"]);
$age=$_POST["Age"];
$phone=$_POST["Mobile"];
$query="INSERT INTO student (Name,Email,Password,Age,Mobile)VALUES('$name','$email','$password','$age','$phone')";
$result=mysqli_query($conn, $query) or die("Error" .mysqli_error($conn));



?>