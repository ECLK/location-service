from django.db import models

# Create your models here.
class Locations(models.Model):
    loc_code=models.CharField(max_length=15, primary_key=True)
    loc_name_sin=models.CharField(max_length=250)
    loc_name_tam=models.CharField(max_length=250)
    loc_name_eng=models.CharField(max_length=250)
    loc_east=models.FloatField()
    loc_north=models.FloatField()
    loc_gnd_id=models.CharField(max_length=20)
    ins_status=models.BooleanField(default=True)
    ins_created=models.DateTimeField(auto_now_add= True)
    ins_updated=models.DateTimeField(auto_now= True)

class Location_contacts(models.Model):
    loc_contact_type=models.CharField(max_length=20)
    loc_contact_details=models.CharField(max_length=200)
    loc_code=models.ForeignKey(Locations, on_delete=models.CASCADE)
    ins_status=models.BooleanField(default=True)
    ins_created=models.DateTimeField(auto_now_add= True)
    ins_updated=models.DateTimeField(auto_now= True)

class Media_items(models.Model):
    mi_title=models.CharField(max_length=150)
    mi_desc=models.CharField(max_length=250)
    mi_file_name=models.CharField(max_length=250)
    mi_file_type=models.CharField(max_length=250)
    mi_lat=models.FloatField()
    mi_lon=models.FloatField()
    mi_loc_code=models.ForeignKey(Locations, on_delete=models.CASCADE)
    ins_status=models.BooleanField(default=True)
    ins_created=models.DateTimeField(auto_now_add= True)
    ins_updated=models.DateTimeField(auto_now= True)



    
