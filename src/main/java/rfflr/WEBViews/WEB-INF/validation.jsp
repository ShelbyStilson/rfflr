<!DOCTYPE html>

<html>
	<head>
	    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <!-- Latest compiled and minified JavaScript -->
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	</head>

	<body>
        <div class="col-lg-12">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
            <h1> PLEASE SCAN YOUR BADGE : </h1>

            <br>

             <form action="" method="POST" role="form">
                <legend>Entry</legend>

                <div class="form-group">
                    <form:form method="post" commandName="validateEntry">
                        <label for="entry">Entry : </label>
                        <form:input type="text" path="entry" class="form-control" id="entry" placeholder="Input field"></form:input>
                    </form:form>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
             </form>
             </div>       
            <div class="col-lg-3">
                
                <img src="http://www.raptortech.com/raptor.png" class="img-responsive" alt="Image">

            </div>
        </div>
	 </body>
 </html>