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