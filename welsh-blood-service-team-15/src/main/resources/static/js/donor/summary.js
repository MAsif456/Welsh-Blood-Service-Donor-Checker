document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.getElementById("toggle-questionnaire");
    const questionnaireDiv = document.querySelector(".questionnaire-answers");

    toggleButton.addEventListener("click", () => {
        const isHidden = questionnaireDiv.classList.toggle("hidden");
        toggleButton.textContent = isHidden ? "Show Questionnaire Answers" : "Hide Questionnaire Answers";
    });
});
