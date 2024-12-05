document.addEventListener('DOMContentLoaded', function() {
    const question1 = document.getElementById('question1');
    const question4 = document.getElementById('question4');
    const question1Section = question1.parentElement;
    const question4Section = question4.parentElement;
    const countrySection = document.getElementById('country-section');
    const regionSection = document.getElementById('region-section');
    const dateSection = document.getElementById('date-section');
    const submitButton = document.getElementById('submit-button');
    const reloadButton = document.getElementById('reload-button');

    function toggleConditionalQuestions() {
        const showExtraFields = (question1.value === 'Yes' || question4.value === 'Yes');

        // Show/hide the extra fields and the submit button based on the condition
        if (showExtraFields) {
            question1Section.style.display = 'none';
            question4Section.style.display = 'none';
            countrySection.style.display = 'block';
            regionSection.style.display = 'block';
            dateSection.style.display = 'block';
            submitButton.style.display = 'block';
            reloadButton.style.display = 'block';
        } else {
            question1Section.style.display = 'block';
            question4Section.style.display = 'block';
            countrySection.style.display = 'none';
            regionSection.style.display = 'none';
            dateSection.style.display = 'none';
            submitButton.style.display = 'none';
            reloadButton.style.display = 'none';
        }
    }

    // Initial check to set visibility based on current values
    toggleConditionalQuestions();

    // Add event listeners for change events
    question1.addEventListener('change', toggleConditionalQuestions);
    question4.addEventListener('change', toggleConditionalQuestions);
});


async function loadRegions(countrySelect, countryKey) {
    const countryId = countrySelect.value;
    const regionSelect = document.getElementById('region');
    const regionSection = document.getElementById('region-section');

    if (countryId) {
        try {
            const response = await fetch(`/api/region/get-region?countryId=${countryId}`);
            const data = await response.json();

            // Clear existing options and set a default option
            regionSelect.innerHTML = '<option value="">Select a region</option>';

            if (data.length > 0) {
                // Populate region options if data is available
                data.forEach(region => {
                    const option = document.createElement('option');
                    option.value = region.regionId;
                    option.textContent = region.regionName;
                    regionSelect.appendChild(option);
                });
                regionSection.style.display = 'block'; // Show the region section
            } else {
                // Hide the region section if no regions are returned
                regionSection.style.display = 'none';
            }
        } catch (error) {
            console.error('Error fetching regions:', error);
            regionSection.style.display = 'none'; // Hide the region section on error
        }
    } else {
        regionSection.style.display = 'none'; // Hide if no country is selected
    }
}


function validateForm(event) {
    const formData = document.forms['travelForm'];
    const question1 = formData['question1'].value;
    const question4 = formData['question4'].value;
    const currentDate = new Date();
    const twelveMonthsAgo = new Date();
    twelveMonthsAgo.setFullYear(currentDate.getFullYear() - 1);

    // Validate "Travelled Abroad in the Last 12 Months" section
    if (question1 === 'Yes') {
        const countriesContainer1 = document.getElementById('countriesContainer1');
        if (countriesContainer1) {
            const countryDivs = countriesContainer1.children;
            for (let i = 0; i < countryDivs.length; i++) {
                const countrySelect = countryDivs[i].querySelector('select');
                const dateInput = countryDivs[i].querySelector('input[type="date"]');
                const selectedDate = new Date(dateInput.value);

                if (
                    !countrySelect || !countrySelect.value ||
                    !dateInput || !dateInput.value ||
                    selectedDate > currentDate || selectedDate < twelveMonthsAgo
                ) {
                    alert('Please ensure all fields are filled for countries visited in the last 12 months. Dates must be within the past year.');
                    event.preventDefault(); // Prevent form submission
                    return false;
                }
            }
        }
    }

    // Validate "Lived in Another Country" section
    if (question4 === 'Yes') {
        const countriesContainer2 = document.getElementById('countriesContainer2');
        if (countriesContainer2) {
            const countryDivs = countriesContainer2.children;
            for (let i = 0; i < countryDivs.length; i++) {
                const countrySelect = countryDivs[i].querySelector('select');
                const dateInput = countryDivs[i].querySelector('input[type="date"]');

                if (!countrySelect || !countrySelect.value || !dateInput || !dateInput.value) {
                    alert('Please ensure all fields are filled for countries you have lived in.');
                    event.preventDefault(); // Prevent form submission
                    return false;
                }
            }
        }
    }

    // If everything is valid, allow form submission
    return true;
}
