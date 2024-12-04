function toggleConditionalQuestions() {
    const formData = document.forms['travelForm'];
    const question1 = formData['question1'].value;
    document.getElementById('conditional1').style.display = question1 === 'Yes' ? 'block' : 'none';

    const question4 = formData['question4'].value;
    document.getElementById('conditional2').style.display = question4 === 'Yes' ? 'block' : 'none';
}

function addCountry(containerId, countryKeyPrefix, dateKeyPrefix, labelText) {
    const container = document.getElementById(containerId);
    const countryIndex = container.children.length;

    const countryDiv = document.createElement('div');
    countryDiv.className = 'form-section';
    countryDiv.id = `${containerId}_div_${countryIndex}`;

    const countryLabel = document.createElement('label');
    countryLabel.setAttribute('for', `${countryKeyPrefix}${countryIndex}`);
    countryLabel.textContent = labelText;
    countryDiv.appendChild(countryLabel);

    const countrySelect = document.createElement('select');
    countrySelect.id = `${countryKeyPrefix}${countryIndex}`;
    countrySelect.name = `${countryKeyPrefix}${countryIndex}`;
    countrySelect.innerHTML = document.getElementById('countryOptions').innerHTML;
    countrySelect.onchange = function() {
        loadRegions(this, `${countryKeyPrefix}${countryIndex}`);
    };
    countryDiv.appendChild(countrySelect);

    // Region input (multi-select)
    const regionDiv = document.createElement('div');
    regionDiv.id = `regionContainer_${countryIndex}`;
    regionDiv.style.display = 'none'; // Hidden by default

    const regionLabel = document.createElement('label');
    regionLabel.setAttribute('for', `region_${countryIndex}`);
    regionLabel.textContent = 'Select a region';
    regionDiv.appendChild(regionLabel);

    const regionSelect = document.createElement('select');
    regionSelect.id = `region_${countryIndex}`;
    regionSelect.name = `region[]`; // Changed to allow array-style submission
    regionSelect.multiple = true; // Allow multiple selections
    regionDiv.appendChild(regionSelect);

    countryDiv.appendChild(regionDiv);

    // Date input
    const dateLabel = document.createElement('label');
    dateLabel.setAttribute('for', `${dateKeyPrefix}${countryIndex}`);
    dateLabel.textContent = 'When did you return from this country?';
    countryDiv.appendChild(dateLabel);

    const dateInput = document.createElement('input');
    dateInput.type = 'date';
    dateInput.id = `${dateKeyPrefix}${countryIndex}`;
    dateInput.name = `${dateKeyPrefix}${countryIndex}`;
    countryDiv.appendChild(dateInput);

    const deleteButton = document.createElement('button');
    deleteButton.type = 'button';
    deleteButton.textContent = 'Delete';
    deleteButton.onclick = function() {
        container.removeChild(countryDiv);
    };
    countryDiv.appendChild(deleteButton);

    container.appendChild(countryDiv);
}

function gatherRegionData() {
    // Collect all selected region values from the form as an array
    const regionData = [];
    document.querySelectorAll('[name="region[]"]').forEach(regionSelect => {
        Array.from(regionSelect.options).forEach(option => {
            if (option.selected) {
                regionData.push(option.value);
            }
        });
    });
    return regionData;
}

// Call this function before submitting the form to include region data in the payload
document.forms['travelForm'].onsubmit = function(event) {
    const regionData = gatherRegionData();
    console.log('Collected Region Data:', regionData);

    // Add the collected region data to the form as a hidden input if needed
    const hiddenRegionInput = document.createElement('input');
    hiddenRegionInput.type = 'hidden';
    hiddenRegionInput.name = 'regions';
    hiddenRegionInput.value = JSON.stringify(regionData);
    this.appendChild(hiddenRegionInput);
};


async function loadRegions(countrySelect, countryKey) {
    const countryId = countrySelect.value;
    const regionContainer = document.getElementById(`regionContainer_${countryKey.split('_')[1]}`);
    const regionSelect = document.getElementById(`region_${countryKey.split('_')[1]}`);

    if (countryId) {
        fetch(`/api/region/get-region?countryId=${countryId}`)
            .then(response => response.json())
            .then(data => {
                // Clear the region select options
                regionSelect.innerHTML = '<option value="">Select a region</option>';

                if (data.length > 0) {
                    // Populate region options if data is available
                    data.forEach(region => {
                        const option = document.createElement('option');
                        option.value = region.regionId;
                        option.textContent = region.regionName;
                        regionSelect.appendChild(option);
                    });
                    regionContainer.style.display = 'block'; // Show the region section
                } else {
                    // If no regions are returned, hide the region section
                    regionContainer.style.display = 'none';
                }
            })
            .catch(error => {
                console.error('Error fetching regions:', error);
                regionContainer.style.display = 'none'; // Hide the region section on error
            });
    } else {
        regionContainer.style.display = 'none'; // Hide if no country is selected
    }
}



function validateForm(event) {
    const formData = document.forms['travelForm'];
    const question1 = formData['question1'].value;
    const question4 = formData['question4'].value;
    const currentDate = new Date();
    const twelveMonthsAgo = new Date();
    twelveMonthsAgo.setFullYear(currentDate.getFullYear() - 1);

    if (question1 === 'Yes') {
        const countryDivs = document.getElementById('countriesContainer1').children;
        for (let i = 0; i < countryDivs.length; i++) {
            const countrySelect = countryDivs[i].querySelector('select');
            const dateInput = countryDivs[i].querySelector('input[type="date"]');
            const selectedDate = new Date(dateInput.value);

            if (!countrySelect.value || !dateInput.value || selectedDate > currentDate || selectedDate < twelveMonthsAgo) {
                alert('Please fill out all fields for the countries you have travelled to in the last 12 months.');
                event.preventDefault(); // Prevent form submission
                return;
            }
        }
    }

    if (question4 === 'Yes') {
        const countryDivs = document.getElementById('countriesContainer2').children;
        for (let i = 0; i < countryDivs.length; i++) {
            const countrySelect = countryDivs[i].querySelector('select');
            const dateInput = countryDivs[i].querySelector('input[type="date"]');

            if (!countrySelect.value || !dateInput.value) {
                alert('Please fill out all fields for the countries you have lived in.');
                event.preventDefault(); // Prevent form submission
                return;
            }
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const regionSelects = document.querySelectorAll('select[multiple]');

    regionSelects.forEach(select => {
        select.addEventListener('click', (event) => {
            const options = select.options;
            const isMultiSelect = select.multiple;

            if (isMultiSelect) {
                // Toggle the 'selected' state on click
                event.preventDefault();
                for (let i = 0; i < options.length; i++) {
                    if (options[i].selected) {
                        options[i].selected = false;
                    } else {
                        options[i].selected = true;
                    }
                }
            }
        });
    });
});

