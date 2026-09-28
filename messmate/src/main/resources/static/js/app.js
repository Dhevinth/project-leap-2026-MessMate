const API = "/api";


function formatDate(date) {

    return date
        .toISOString()
        .split("T")[0];

}


function today() {

    return formatDate(
        new Date()
    );

}


async function getData(url) {

    const response =
        await fetch(url);

    if (!response.ok) {

        throw new Error(
            "Request failed"
        );

    }

    return response.json();

}


/* ===============================
   HOME PAGE
================================ */

async function loadHomeData() {

    try {

        const date =
            today();


        const meals =
            await getData(
                `${API}/meals?from=${date}&to=${date}`
            );


        const items =
            await getData(
                `${API}/menu-items`
            );


        const residents =
            await getData(
                `${API}/residents`
            );


        document.getElementById(
            "mealCount"
        )?.replaceChildren(
            document.createTextNode(
                meals.length
            )
        );


        document.getElementById(
            "menuCount"
        )?.replaceChildren(
            document.createTextNode(
                items.length
            )
        );


        document.getElementById(
            "residentCount"
        )?.replaceChildren(
            document.createTextNode(
                residents.length
            )
        );


        document.getElementById(
            "reportMeals"
        )?.replaceChildren(
            document.createTextNode(
                meals.length
            )
        );


        document.getElementById(
            "reportItems"
        )?.replaceChildren(
            document.createTextNode(
                items.length
            )
        );


        const rating =
            await getData(
                `${API}/reports/average/day?date=${date}`
            );


        if (Number(rating) > 0) {

            const value =
                Number(rating).toFixed(1);

            document.getElementById(
                "averageRating"
            )?.replaceChildren(
                document.createTextNode(value)
            );

            document.getElementById(
                "reportRating"
            )?.replaceChildren(
                document.createTextNode(value)
            );

        }


        loadTodayMenu(meals);


    } catch (error) {

        console.error(
            "Home loading error:",
            error
        );

    }

}


/* ===============================
   TODAY MENU
================================ */

function loadTodayMenu(meals) {

    const container =
        document.getElementById(
            "todayMenu"
        );

    if (!container) {
        return;
    }


    if (!meals.length) {

        container.innerHTML = `
            <div class="empty-state">
                No meals scheduled for today.
            </div>
        `;

        return;

    }


    container.innerHTML = "";


    meals.forEach(meal => {

        const card =
            document.createElement(
                "div"
            );

        card.className =
            "meal-card";


        const items =
            meal.menuItems || [];


        card.innerHTML = `

            <span class="small-title">
                ${meal.mealType}
            </span>

            <h3>
                ${meal.mealType}
            </h3>

            <div class="menu-list">

                ${
            items.length

                ? items.map(item =>
                    `
                        <span class="menu-tag">
                            ${escapeHTML(item.name)}
                        </span>
                        `
                ).join("")

                : `
                        <span>
                            Menu not available
                        </span>
                    `
        }

            </div>

        `;


        container.appendChild(card);

    });

}


/* ===============================
   DATE
================================ */

function showCurrentDate() {

    const element =
        document.getElementById(
            "currentDate"
        );

    if (!element) {
        return;
    }


    element.textContent =
        new Date().toLocaleDateString(
            "en-IN",
            {
                weekday: "long",
                day: "numeric",
                month: "long",
                year: "numeric"
            }
        );

}


function escapeHTML(text) {

    const div =
        document.createElement(
            "div"
        );

    div.textContent =
        text ?? "";

    return div.innerHTML;

}


/* ===============================
   INITIALIZE
================================ */

document.addEventListener(
    "DOMContentLoaded",
    () => {

        showCurrentDate();

        loadHomeData();

    }
);