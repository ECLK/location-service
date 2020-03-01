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

class MinistriesSerializer(serializers.ModelSerializer):
    class Meta:
        model=models.Ministries
        fields = ('id','name_sinhala','name_tamil','name_english','status')

class CommissionsSerializer(serializers.ModelSerializer):
    class Meta:
        model=models.Commissions
        fields = ('id','name_sinhala','name_tamil','name_english','status')

class LocalAuthoritiesSerializer(serializers.ModelSerializer):
    electoral_district=ElectroaldistrictSerializer(many=False)
    class Meta:
        model=models.LocalAuthorities
        fields = ('id','name_sinhala','name_tamil','name_english','electoral_district','status')

class DepartmentsSerializer(serializers.ModelSerializer):
    ministry=MinistriesSerializer(many=False)
    class Meta:
        model=models.Departments
        fields = ('id','name_sinhala','name_tamil','name_english','ministry','status')

class BranchesSerializer(serializers.ModelSerializer):
    department=DepartmentsSerializer(many=False)
    class Meta:
        model=models.Branches
        fields = ('id','name_sinhala','name_tamil','name_english','department','status')


class DivisionalsecretariatsSerializer(serializers.ModelSerializer):
    admin_district=AdmindistrictSerializer(many=False)
    class Meta:
        model=models.Divisionalsecretariats
        fields = ('id','name_sinhala','name_tamil','name_english','admin_district','status')

class ProvincialcouncilsSerializer(serializers.ModelSerializer):
    class Meta:
        model=models.Provincialcouncils
        fields = ('id','name_sinhala','name_tamil','name_english','status')
<<<<<<< HEAD

class PolicedivisionsSerializer(serializers.ModelSerializer):
    provice=ProvincialcouncilsSerializer(many=False)
    class Meta:
        model=models.Policedivisions
        fields = ('id','name_sinhala','name_tamil','name_english','provice','status')

class PolicestationsSerializer(serializers.ModelSerializer):
    police_division=PolicedivisionsSerializer(many=False)
    class Meta:
        model=models.Policestations
        fields = ('id','name_sinhala','name_tamil','name_english','police_division','status')


class ProvincialministriesSerializer(serializers.ModelSerializer):
    province=ProvincialcouncilsSerializer(many=False)
    class Meta:
        model=models.Provincialministries
        fields = ('id','name_sinhala','name_tamil','name_english','province','status')

class ProvincialministrydemartmentsSerializer(serializers.ModelSerializer):
    province=ProvincialministriesSerializer(many=False)
    class Meta:
        model=models.Provincialministrydemartments
        fields = ('id','name_sinhala','name_tamil','name_english','province_ministry','status')
=======
>>>>>>> a44f7984ad282c2b8008e716b9c2af303f5781f2
