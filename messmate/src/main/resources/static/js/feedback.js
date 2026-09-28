const API = "/api";

let selectedRating = 0;


document.addEventListener("DOMContentLoaded", function () {

    loadResidents();

    loadMeals();

    setupRating();

    setupCommentCounter();

    setupForm();

});


/* =========================
   LOAD RESIDENTS
========================= */

async function loadResidents() {

    const select =
        document.getElementById("residentId");

    try {

        const response =
            await fetch(`${API}/residents`);

        if (!response.ok) {
            throw new Error();
        }

        const residents =
            await response.json();


        residents.forEach(function (resident) {

            const option =
                document.createElement("option");

            option.value = resident.id;

            option.textContent =
                `${resident.name} - Room ${resident.roomNo}`;

            select.appendChild(option);

        });

    } catch (error) {

        showMessage(
            "Unable to load residents.",
            "error"
        );

    }

}


/* =========================
   LOAD MEALS
========================= */

async function loadMeals() {

    const select =
        document.getElementById("mealId");


    const today =
        new Date();

    const from =
        formatDate(today);


    const end =
        new Date(today);

    end.setDate(
        end.getDate() + 6
    );


    const to =
        formatDate(end);


    try {

        const response =
            await fetch(
                `${API}/meals?from=${from}&to=${to}`
            );


        if (!response.ok) {
            throw new Error();
        }


        const meals =
            await response.json();


        meals.forEach(function (meal) {

            const option =
                document.createElement("option");


            option.value =
                meal.id;


            const type =
                formatMealType(meal.mealType);


            option.textContent =
                `${meal.mealDate} - ${type}`;


            select.appendChild(option);

        });


    } catch (error) {

        showMessage(
            "Unable to load meals.",
            "error"
        );

    }

}


/* =========================
   RATING
========================= */

function setupRating() {

    const buttons =
        document.querySelectorAll(
            ".rating-buttons button"
        );


    buttons.forEach(function (button) {

        button.addEventListener(
            "click",
            function () {

                selectedRating =
                    Number(this.dataset.rating);


                document
                    .getElementById("rating")
                    .value = selectedRating;


                buttons.forEach(function (item) {

                    item.classList.remove(
                        "selected"
                    );

                });


                this.classList.add("selected");


                document.getElementById(
                    "ratingText"
                ).textContent =
                    getRatingText(selectedRating);

            }
        );

    });

}


function getRatingText(rating) {

    switch (rating) {

        case 1:
            return "Very poor";

        case 2:
            return "Needs improvement";

        case 3:
            return "Average";

        case 4:
            return "Good";

        case 5:
            return "Excellent";

        default:
            return "Select a rating";

    }

}


/* =========================
   COMMENT COUNTER
========================= */

function setupCommentCounter() {

    const comment =
        document.getElementById("comment");

    const counter =
        document.getElementById("charCount");


    comment.addEventListener(
        "input",
        function () {

            counter.textContent =
                this.value.length;

        }
    );

}


/* =========================
   FORM
========================= */

function setupForm() {

    const form =
        document.getElementById("feedbackForm");


    form.addEventListener(
        "submit",
        async function (event) {

            event.preventDefault();


            const residentId =
                document.getElementById(
                    "residentId"
                ).value;


            const mealId =
                document.getElementById(
                    "mealId"
                ).value;


            const comment =
                document.getElementById(
                    "comment"
                ).value.trim();


            if (!residentId) {

                showMessage(
                    "Please select a resident.",
                    "error"
                );

                return;
            }


            if (!mealId) {

                showMessage(
                    "Please select a meal.",
                    "error"
                );

                return;
            }


            if (selectedRating < 1 ||
                selectedRating > 5) {

                showMessage(
                    "Please select a rating from 1 to 5.",
                    "error"
                );

                return;
            }


            const button =
                form.querySelector(
                    ".submit-button"
                );


            button.disabled = true;

            button.textContent =
                "Submitting...";


            try {

                const response =
                    await fetch(
                        `${API}/feedback`,
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body: JSON.stringify({

                                residentId:
                                    Number(residentId),

                                mealId:
                                    Number(mealId),

                                rating:
                                selectedRating,

                                comment:
                                    comment || null

                            })

                        }
                    );


                const data =
                    await response.json();


                if (!response.ok) {

                    throw new Error(
                        data.error ||
                        "Unable to submit feedback."
                    );

                }


                showMessage(
                    "Feedback submitted successfully.",
                    "success"
                );


                form.reset();


                selectedRating = 0;


                document
                    .querySelectorAll(
                        ".rating-buttons button"
                    )
                    .forEach(function (item) {

                        item.classList.remove(
                            "selected"
                        );

                    });


                document.getElementById(
                    "ratingText"
                ).textContent =
                    "Select a rating";


                document.getElementById(
                    "charCount"
                ).textContent = "0";


            } catch (error) {

                showMessage(
                    error.message ||
                    "Unable to submit feedback.",
                    "error"
                );

            } finally {

                button.disabled = false;

                button.textContent =
                    "Submit Feedback";

            }

        }
    );

}


/* =========================
   MESSAGE
========================= */

function showMessage(text, type) {

    const message =
        document.getElementById("message");


    message.textContent = text;

    message.className = type;


    setTimeout(function () {

        message.className = "";

    }, 5000);

}


/* =========================
   HELPERS
========================= */

function formatMealType(type) {

    if (!type) {
        return "Meal";
    }


    return type.charAt(0) +
        type.substring(1).toLowerCase();

}


function formatDate(date) {

    return date.toISOString()
        .split("T")[0];

}