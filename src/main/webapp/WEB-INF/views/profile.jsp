<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css" />
    <title>User profile</title>
</head>

<body class="text-gray-800 antialiased">
<jsp:include flush="true" page="./fragments/header.jsp"/>
<main class="profile-page">
    <section class="relative block" style="height: 500px;">
        <div class="absolute top-0 w-full h-full bg-center bg-cover" style='background-image: url("./assets/img/header-profil.jpg");'>
        </div>
        <div class="top-auto bottom-0 left-0 right-0 w-full absolute pointer-events-none overflow-hidden" style="height: 70px; transform: translateZ(0px);" >
            <svg class="absolute bottom-0 overflow-hidden" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" version="1.1" viewBox="0 0 2560 100" x="0" y="0" >
                <polygon class="text-gray-300 fill-current" points="2560 0 2560 100 0 100" ></polygon>
            </svg>
        </div>
    </section>
    <section class="relative py-16 bg-gray-300">
        <div class="container mx-auto px-4">
            <div class="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-xl rounded-lg -mt-64" >
                <div class="px-6">
                    <div class="flex flex-wrap justify-center">
                        <div class="w-full lg:w-3/12 px-4 lg:order-2 flex justify-center" >
                            <div class="relative">
                                <img alt="..." src="./assets/img/goat.png" class="shadow-xl rounded-full h-auto align-middle border-none absolute -m-16 -ml-20 lg:-ml-16" style="max-width: 150px;"/>
                            </div>
                        </div>
                        <div class="w-full lg:w-4/12 px-4 lg:order-3 lg:text-right lg:self-center" >
                        </div>
                        <div class="w-full lg:w-4/12 px-4 lg:order-1">
                            <div class="flex justify-center py-4 lg:pt-4 pt-8">
                                <div class="mr-4 p-3 text-center">
                                    <span class="text-xl font-bold block uppercase tracking-wide text-gray-700">${nbrQuestions}</span>
                                    <span class="text-sm text-gray-500">Questions</span>
                                </div>
                                <div class="mr-4 p-3 text-center">
                                    <span class="text-xl font-bold block uppercase tracking-wide text-gray-700">${nbrAnswers}</span>
                                    <span class="text-sm text-gray-500">Answer</span>
                                </div>
                                <div class="lg:mr-4 p-3 text-center">
                                    <span class="text-xl font-bold block uppercase tracking-wide text-gray-700" >${nbrComments}</span>
                                    <span class="text-sm text-gray-500">Comments</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center mt-12">
                        <h3 class="text-4xl font-semibold leading-normal mb-2 text-gray-800 mb-2">
                            ${currentUser.firstname} ${currentUser.lastname}
                        </h3>
                        <h4 class="title">${currentUser.username}</h4>
                        <div class="text-sm leading-normal mt-0 mb-2 text-gray-500 font-bold uppercase">
                            ${currentUser.email}
                        </div>
                    </div>
                    <div>
                        <form action="./profile.do" method="POST">
                            <h2>Change Password:</h2>
                            <input type="password" name="newClearTextPassword" id="newClearTextPassword" class="form-control" placeholder="New password">
                            <button>Update password</button>
                        </form>
                        <c:forEach var="error" items="${errors}">
                            <div class="error">${error}</div>
                        </c:forEach>
                    </div>
                    <div>
                        <form action="./profile.up" method="POST">
                            <h2>
                                Update your profile
                            </h2>
                            <input type="text" name="newLastname" id="newLastname" class="form-control"
                                   placeholder="New lastname">
                            <input type="text" name="newFirstname" id="newFirstname" class="form-control"
                                   placeholder="New firstname">
                            <input type="text" name="newEmail" id="newEmail" class="form-control"
                                   placeholder="New email">
                            <button>
                                Change profile information
                            </button>
                        </form>
                        <c:forEach var="error" items="${errors}">
                            <div class="error">${error}</div>
                        </c:forEach>
                    </div>

                    <div class="mt-10 py-10 border-t border-gray-300 text-center">
                        <h3 class="text-3xl font-semibold leading-normal mb-2 text-orange-800 mb-2">
                            Questions History
                        </h3>
                        <div class="flex flex-wrap justify-center">
                            <div class="w-full lg:w-9/12 px-4">
                                <c:forEach var="question" items="${questions.questions}">
                                    <div> <!-- one per question-->
                                        <p class="mb-4 text-lg leading-relaxed text-gray-800" align="justify">
                                            <a href="/question?id=${question.id}" class="a">${question.text}</a>
                                        </p>
                                        <form action="./removeQuestion.do" method="POST">
                                            <input name="questionId" type="hidden" value="${question.id}">
                                            <input name="userId" type="hidden" value="${currentUser.id}">
                                            <button
                                                    type="submit"
                                                    class="bg-green-500 active:bg-green-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                                                    style="transition: all 0.15s ease 0s;">
                                                Remove
                                            </button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="mt-10 py-10 border-t border-gray-300 text-center">
                        <h3 class="text-3xl font-semibold leading-normal mb-2 text-orange-800 mb-2">
                            Answers History
                        </h3>
                        <div class="flex flex-wrap justify-center">
                            <div class="w-full lg:w-9/12 px-4">
                                <c:forEach var="answer" items="${answers.answers}">
                                    <div> <!-- one per question-->
                                        <p class="mb-4 text-lg leading-relaxed text-gray-800" align="justify">
                                            <a href="/question?id=${answer.idQuestion}" class="a">${answer.text}</a>
                                        </p>
                                        <form action="./removeAnswer.do" method="POST">
                                            <input name="answerId" type="hidden" value="${answer.id}">
                                            <input name="userId" type="hidden" value="${currentUser.id}">
                                            <button
                                                    type="submit"
                                                    class="bg-green-500 active:bg-green-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                                                    style="transition: all 0.15s ease 0s;">
                                                Remove
                                            </button>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="mt-10 py-10 border-t border-gray-300 text-center">
                        <h3 class="text-3xl font-semibold leading-normal mb-2 text-orange-800 mb-2">
                            Comments History
                        </h3>
                        <div class="flex flex-wrap justify-center">
                            <div class="w-full lg:w-9/12 px-4">
                                <h4 class="text-3xl font-semibold leading-normal mb-2 text-orange-800 mb-2">
                                    Question Comments
                                </h4>
                                <c:forEach var="comment" items="${commentsQ.comments}">
                                    <div> <!-- one per question-->
                                        <p class="mb-4 text-lg leading-relaxed text-gray-800" align="justify">
                                            <a href="/question?id=${comment.idSubject}" class="a">${comment.comment}</a>
                                        </p>
                                        <form action="./removeQuestionComment.do" method="POST">
                                            <input name="commentId" type="hidden" value="${comment.id}">
                                            <button
                                                    class="bg-green-500 active:bg-green-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                                                    type="submit"
                                                    style="transition: all 0.15s ease 0s;">
                                                Remove
                                            </button>
                                        </form>
                                    </div>
                                </c:forEach>
                                <h4 class="text-3xl font-semibold leading-normal mb-2 text-orange-800 mb-2">
                                    Answer Comments
                                </h4>
                                <div>
                                    <c:forEach var="comment" items="${commentsA.comments}">
                                        <div> <!-- one per question-->
                                            <p class="mb-4 text-lg leading-relaxed text-gray-800" align="justify">
                                                    ${comment.comment}
                                            </p>
                                            <form action="./removeAnswerComment.do" method="POST">
                                                <input name="commentId" type="hidden" value="${comment.id}">
                                                <button
                                                        class="bg-green-500 active:bg-green-600 uppercase text-white font-bold hover:shadow-md shadow text-xs px-4 py-2 rounded outline-none focus:outline-none sm:mr-2 mb-1"
                                                        type="submit"
                                                        style="transition: all 0.15s ease 0s;">
                                                    Remove
                                                </button>
                                            </form>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
