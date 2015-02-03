<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
		<script src="resources/js/bootstrap.min.js"></script>

            <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Latest compiled and minified JavaScript -->
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	</head>

	<body>

        <div class="col-lg-12">
            <div class="col-lg-3">
                
                <img src="resources/images/raptor.png" class="img-responsive" alt="Image">

            </div>
            <div class="col-lg-6">
                <h1> Select a Ticket Amount : </h1>

                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default">1</button>
                    <button type="button" class="btn btn-default">2</button>
                    <button type="button" class="btn btn-default">3</button>
                    <button type="button" class="btn btn-default">4</button>
                    <button type="button" class="btn btn-default">5</button>
                    <button type="button" class="btn btn-default">10</button>
                    <button type="button" class="btn btn-default">15</button>
                    <button type="button" class="btn btn-default">20</button>
                </div>

                <form:form action="" method="POST" role="form" commandName="ticketAddition">
                    <legend>Entry</legend>

                    <div class="form-group">
                        <label for="amount">Amount : </label>
                        <form:input type="text" path="amount" class="form-control" id="amount" placeholder="Input field"></form:input>
                        <label for="entry">Entry : </label>
                        <form:input type="text" path="entry" class="form-control" id="entry" placeholder="Input field"></form:input>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form:form>

                <button type="button" href="pickPrize.html" class="btn btn-default">Prizes</button>

                

            </div>
            <div class="col-lg-3"></div>
        </div>

		<br>



	 </body>
 </html>