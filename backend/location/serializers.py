from rest_framework import serializers
from . import models

class ElectroaldistrictSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = models.Electroaldistrict
        fields = ('id','name_sinhala','name_tamil','name_english', 'ed_status')

class AdmindistrictSerializer(serializers.ModelSerializer):
    electoral_district=ElectroaldistrictSerializer(many=False)
    class Meta:
        model = models.Admindistrict
        fields = ('id','name_sinhala','name_tamil','name_english','electoral_district','district_status')


class PolingdivisionSerializer(serializers.ModelSerializer):
    electoral_district=ElectroaldistrictSerializer(many=False)
    class Meta:
        model = models.Polingdivision
        fields = ('id','name_sinhala','name_tamil','name_english','pd_status','electoral_district')

class GramaniladaridivisionSerializer(serializers.ModelSerializer):
    polingdivision=PolingdivisionSerializer(many=False)
    class Meta:
        model = models.Gramaniladaridivision
        fields = ('gnd_code','name_sinhala','name_tamil','name_english','polingdivision','gdn_status')

class LocationsSerializer(serializers.ModelSerializer):
    gdn=GramaniladaridivisionSerializer(many=False)
    class Meta:
        model = models.Locations
        fields = ('code','name_sinhala','name_tamil','name_english','coordinate_east','coordinate_north','latitude','longitute','gdn','location_status')

class Location_contactsSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Location_contacts
        fields = '__all__'

class Media_itemsSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Media_items
        fields = '__all__'
