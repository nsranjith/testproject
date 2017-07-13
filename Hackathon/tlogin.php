<?php
require 'DB_Connect.php';
$email=$_POST["Email"];
$password=md5($_POST["Password"]);
$query="SELECT Email,Password from tutor where Email='$email' and Password='$password'";
$result=mysqli_query($conn, $query) or die("Error" .mysqli_error($conn));
if(mysqli_num_rows($result)==1){
$response["error"]=FALSE;
}
else
{
$response["error"]=$password;

}
echo json_encode($response);
?>