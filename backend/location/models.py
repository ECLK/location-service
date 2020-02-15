from django.db import models

# Create your models here.
class Locations(models.Model):
    code=models.CharField(max_length=15, primary_key=True)
    name_sinhala=models.CharField(max_length=250)
    name_tamil=models.CharField(max_length=250)
    name_english=models.CharField(max_length=250)
    coordinate_east=models.FloatField()
    coordinate_north=models.FloatField()
    gdn_id=models.ForeignKey(Gramaniladaridivision, on_delete=models.CASCADE)
    location_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Location_contacts(models.Model):
    contact_type=models.CharField(max_length=20)
    contact_details=models.CharField(max_length=200)
    location_code=models.ForeignKey(Locations, on_delete=models.CASCADE)
    lc_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Media_items(models.Model):
    title=models.CharField(max_length=150)
    description=models.CharField(max_length=250)
    file_name=models.CharField(max_length=250)
    file_type=models.CharField(max_length=250)
    lat=models.FloatField()
    lon=models.FloatField()
    loc_code=models.ForeignKey(Locations, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class District(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    electoral_district_id=models.ForeignKey(Electroaldistrict, on_delete=models.CASCADE)
    district_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Electroaldistrict(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    ed_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Polingdivision(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    electoral_district_id=models.ForeignKey(Electroaldistrict, on_delete=models.CASCADE)
    pd_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Gramaniladaridivision(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    electoral_district_id=models.ForeignKey(Polingdivision, on_delete=models.CASCADE)
    gdn_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

