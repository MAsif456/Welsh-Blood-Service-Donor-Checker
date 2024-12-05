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
    const finishButton = document.getElementById('finish-button');

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
            finishButton.style.display = 'none';
        } else {
            question1Section.style.display = 'block';
            question4Section.style.display = 'block';
            countrySection.style.display = 'none';
            regionSection.style.display = 'none';
            dateSection.style.display = 'none';
            submitButton.style.display = 'none';
            reloadButton.style.display = 'none';
            finishButton.style.display = 'block';
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
    const countryId = formData['country'].value;
    const regionId = formData['region'].value;
    const date = formData['date'].value;
    const currentDate = new Date();
    const twelveMonthsAgo = new Date();
    twelveMonthsAgo.setFullYear(currentDate.getFullYear() - 1);

    //check country and date are provided
    if (!countryId || !date) {
        alert('Country and date fields are required. Please fill them out before submitting.');
        event.preventDefault(); // Prevent form submission
        return false;
    }

    //if question 1 is yes:
    if (question1 === 'Yes') {
        //date has to be within last 12 months
        //check if the date is within the last 12 months
        const selectedDate = new Date(date);
        if (isNaN(selectedDate.getTime()) || selectedDate > currentDate || selectedDate < twelveMonthsAgo) {
            alert('The date must be within the last 12 months. Please provide a valid date.');
            event.preventDefault(); // Prevent form submission
            return false;
        }
    }

    if (question4 === 'Yes') {
        //date doesn't have to be within last 12 months
        //check date is valid
        const selectedDate = new Date(date);
        if (isNaN(selectedDate.getTime())) {
            alert('The date must be valid. Please provide a valid date.');
            event.preventDefault(); // Prevent form submission
            return false;
        }
    }

    // If all validations pass, allow form submission
    return true;
}


