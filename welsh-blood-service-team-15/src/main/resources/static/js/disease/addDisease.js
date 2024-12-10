async function loadRegions(countrySelect) {
    console.log('loadRegions');
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
            }
            // Add the "Add new region" option
            const addNewOption = document.createElement('option');
            addNewOption.value = 'add-new';
            addNewOption.textContent = 'Add new region';
            regionSelect.appendChild(addNewOption);

            regionSection.style.display = 'block'; // Show the region section
        } catch (error) {
            console.error('Error fetching regions:', error);
            regionSection.style.display = 'block'; // Show the region section on error
        }
    } else {
        regionSection.style.display = 'block'; // Show if no country is selected
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('region').addEventListener('change', function() {
        var newRegionSection = document.getElementById('new-region-section');
        if (this.value === 'add-new') {
            newRegionSection.style.display = 'block';
        } else {
            newRegionSection.style.display = 'none';
        }
    });
});