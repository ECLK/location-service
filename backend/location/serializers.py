from rest_framework import serializers
from . import models

class LocationsSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Locations
        fields = '__all__'

class Location_contactsSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Location_contacts
        fields = '__all__'

class Media_itemsSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Media_items
        fields = '__all__'

class DistrictSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.District
        fields = '__all__'

class ElectroaldistrictSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Electroaldistrict
        fields = '__all__'

class PolingdivisionSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Polingdivision
        fields = '__all__'

class GramaniladaridivisionSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Gramaniladaridivision
        fields = '__all__'