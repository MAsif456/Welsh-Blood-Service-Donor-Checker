document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.getElementById("toggle-questionnaire");
    const questionnaireDiv = document.querySelector(".questionnaire-answers");

    toggleButton.addEventListener("click", () => {
        const isHidden = questionnaireDiv.classList.toggle("hidden");
        toggleButton.textContent = isHidden ? "Show Questionnaire Answers" : "Hide Questionnaire Answers";
    });

    const toggleRecommendationsButton = document.getElementById("toggle-recommendations");
    const recommendationsDiv = document.querySelector(".recommendations");

    toggleRecommendationsButton.addEventListener("click", () => {
        const isHidden = recommendationsDiv.classList.toggle("hidden");
        toggleRecommendationsButton.textContent = isHidden ? "Show Recommendations" : "Hide Recommendations";
    });
});
