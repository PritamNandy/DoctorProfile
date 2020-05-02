<?php

define('DB_HOST','localhost');
define('DB_USER','codearistosio_recyclerview');
define('DB_PASS','codearistos!!!!!');
define('DB_NAME','codearistosio_recyclerview');

$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_USER);

if(mysqli_connect_errno()) {
    die('Unable to connect to database '. mysqli_connect_error());
}

$good = $conn->prepare("SELECT id, name, hospital, rating, visit, image FROM doctors;");

$good->execute();

$good->bind_result($id, $name, $hospital, $rating, $visit, $image);

$doctor = array();

while($good->fetch()) {
    $temp = array();
    $temp['id'] = $id;
    $temp['name'] = $name;
    $temp['hospital'] = $hospital;
    $temp['rating'] = $rating;
    $temp['visit'] = $visit;
    $temp['image'] = $image;
    
    array_push($doctor, $temp);
}

echo json_encode($doctor);