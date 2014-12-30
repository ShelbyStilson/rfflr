<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
		<script src="resources/js/bootstrap.min.js"></script>
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

                <a class="btn btn-primary" data-toggle="modal" href='#added-true'>Trigger modal</a>
                <div class="modal fade" id="added-true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><font color = "green">10</font> Tickets Added</h4>
                            </div>
                            <div class="modal-body">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->


                <a class="btn btn-primary" data-toggle="modal" href='#added-false'>Trigger modal</a>
                <div class="modal fade" id="added-false">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title"><font color = "red">Unsucessful. Please try again.</font></h4>
                            </div>
                            <div class="modal-body">

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

                

            </div>
            <div class="col-lg-3"></div>
        </div>

		<br>



	 </body>
 </html>