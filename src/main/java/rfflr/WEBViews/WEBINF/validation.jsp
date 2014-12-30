<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
		<script src="resources/js/bootstrap.min.js"></script>
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