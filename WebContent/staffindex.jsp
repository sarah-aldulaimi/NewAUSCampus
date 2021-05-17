<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="dbAccess.DataHandler, java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="TemplateMo">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-lava.css">
    <link rel="stylesheet" href="assets/css/owl-carousel.css">   
    <title>Home</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">  
</head>

<body>

    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <!-- ***** Preloader End ***** -->

    <!-- ***** Header Area Start ***** -->
    <header class="header-area header-sticky">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav class="main-nav">
                        <!-- ***** Logo Start ***** -->
                        <a href="index.html" class="logo">
                           AUS
                        </a>
                        <!-- ***** Logo End ***** -->
                        <!-- ***** Menu Start ***** -->
                        <ul class="nav">
                            <li class="scroll-to-section"><a href="#welcome" class="menu-item">Home</a></li>
                            <li class="scroll-to-section"><a href="#book" class="menu-item">Book</a></li>
                            <li class="scroll-to-section"><a href="#faq" class="menu-item">FAQ</a>
                            </li>

								<li class="submenu">
                                <a href="javascript:;"><i class="fa fa-user-circle"></i></a>
                                <ul>
                                    <li><a href="ViewProfile.jsp" >View Profile</a></li>
                             
                                    <li><a href="index.html">Sign out</a></li>
  
                                </ul>
                            </li>
                                                    </ul>
                        <a class='menu-trigger'>
                            <span>Menu</span>
                        </a>
                        <!-- ***** Menu End ***** -->
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ***** Header Area End ***** -->

    <!-- ***** Welcome Area Start ***** -->
    <div class="welcome-area" id="welcome">
    
      <%
   DataHandler data = new DataHandler(); 
   
   ResultSet rs = data.getAccountInformation((int)session.getAttribute("id"));
   String name = rs.getString("fname");
   %>
    
        <!-- ***** Header Text Start ***** -->
        <div class="header-text">
            <div class="container">
                <div class="row">
                    <div class="left-text col-lg-6 col-md-12 col-sm-12 col-xs-12"
                        data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
                        <h1>Welcome <em><% out.println(name); %></em></h1>
                        <p>Due to the recent pandemic access to campus has been extremely limited. Through this service we hope to better provide for students</p> 
                        <a href="#book" class="main-button-slider">Book a class now</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- ***** Header Text End ***** -->
    </div>
    <!-- ***** Welcome Area End ***** -->

    <!-- ***** Features Big Item Start ***** -->
    <section class="section" id="book">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12"
                    data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
                    <div class="features-item">
                        <div class="features-icon">
                            <h2>01</h2>
                            <img src="assets/images/lab.png" alt="">
                            <h4>Book a Lab</h4>
                            <p></p>
                            <a href="BookLabStaff.jsp" class="main-button">Book a Lab</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12"
                    data-scroll-reveal="enter bottom move 30px over 0.6s after 0.4s">
                    <div class="features-item">
                        <div class="features-icon">
                            <h2>02</h2>
                            <img src="assets/images/test.png" alt="">
                            <h4>Book an Exam Room</h4>
                            <p></p>
                            <a href="BookExam.jsp" class="main-button">Book a Room</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12"
                    data-scroll-reveal="enter right move 30px over 0.6s after 0.4s">
                    <div class="features-item">
                        <div class="features-icon">
                            <h2>03</h2>
                            <img src="assets/images/calender.png" alt="">
                            <h4>View Schedule</h4>
                       <form  method="Get" action='InstructorScheduleServlet'>
                            <button class="main-button" type ="submit">Display schedule</button>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Features Big Item End ***** -->
               
    <div class="right-image-decor"></div>
                   

    <!-- ***** Testimonials Starts ***** -->
    <section class="section" id="faq">
    
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="center-heading">
                        <h2><em>Frequently</em> Asked Questions</h2>
                        <p></p>
                    </div>
                </div>
            <div class="col-lg-10 col-md-12 col-sm-12 mobile-bottom-fix-big"
                    data-scroll-reveal="enter left move 30px over 0.6s after 0.4s">
                    <div class="owl-carousel owl-theme">
                        <div class="item service-item">
                 
                            <div class="faq-content">

                                <h4>How recent should my PCR test be?</h4>
                                <p>The last PCR test taken must not have exceeded 15 days </p>
                     
                            </div>
                        </div>
                        <div class="item service-item">
              
                            <div class="faq-content">
      
                                <h4>Why can I not see my reservation?</h4>
                                <p>If you've made a reservation and cannot view it in your schedule, it could mean that it 
                                has not been approved by the administration yet. </p>
                                
                        
                            </div>
                        </div>
                        <div class="item service-item">
            
                            <div class="faq-content">
           
                                <h4>How much time in advance should I make my reservation?</h4>
                                <p>Please make your reservation at least a week in advance.</p>
                           
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** FAQ Ends ***** -->


    <!-- ***** Footer Start ***** -->
<!--     <footer id="contact-us"> -->
<!--         <div class="container"> -->
<!--             <div class="footer-content"> -->
<!--                 <div class="row"> -->
   

<!--                 </div> -->
<!--             </div> -->
 
<!--         </div> -->
<!--     </footer> -->

    <!-- jQuery -->
    <script src="assets/js/jquery-2.1.0.min.js"></script>

    <!-- Bootstrap -->
    <script src="assets/js/popper.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

    <!-- Plugins -->
    <script src="assets/js/owl-carousel.js"></script>
    <script src="assets/js/scrollreveal.min.js"></script>
    <script src="assets/js/waypoints.min.js"></script>
    <script src="assets/js/jquery.counterup.min.js"></script>
    <script src="assets/js/imgfix.min.js"></script>

    <!-- Global Init -->
    <script src="assets/js/custom.js"></script>

</body>
</html>