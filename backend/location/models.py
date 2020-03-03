from django.db import models

# Create your models here.

class Provincialcouncils(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    status=models.BooleanField(default=True)

    class Meta:
        ordering = ('id', )

class Electroaldistrict(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    provice=models.ForeignKey(Provincialcouncils, on_delete=models.CASCADE)
    ed_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )
    
class Admindistrict(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    electoral_district=models.ForeignKey(Electroaldistrict, on_delete=models.CASCADE)
    district_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

class Polingdivision(models.Model):
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    electoral_district=models.ForeignKey(Electroaldistrict, on_delete=models.CASCADE)
    pd_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Gramaniladaridivision(models.Model):
    gnd_code=models.CharField(max_length=15, primary_key=True)
    name_sinhala=models.CharField(max_length=100)
    name_tamil=models.CharField(max_length=100)
    name_english=models.CharField(max_length=100)
    polingdivision=models.ForeignKey(Polingdivision, on_delete=models.CASCADE)
    gdn_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)
    class Meta:
        ordering = ('gnd_code', )

class Locations(models.Model):
    code=models.CharField(max_length=15, primary_key=True)
    name_sinhala=models.CharField(max_length=250)
    name_tamil=models.CharField(max_length=250)
    name_english=models.CharField(max_length=250)
    coordinate_east=models.FloatField()
    coordinate_north=models.FloatField()
    latitude=models.FloatField()
    longitute=models.FloatField()
    gdn=models.ForeignKey(Gramaniladaridivision, on_delete=models.CASCADE)
    location_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('code', )

class Location_contacts(models.Model):
    contact_type=models.CharField(max_length=20)
    contact_details=models.CharField(max_length=200)
    location=models.ForeignKey(Locations, on_delete=models.CASCADE)
    lc_status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)
    
    class Meta:
        ordering = ('id', )

class Media_items(models.Model):
    title=models.CharField(max_length=150)
    description=models.CharField(max_length=250)
    media=models.FileField(null=False,blank=False)
    file_type=models.CharField(max_length=10)
    latitude=models.FloatField(null=True,blank=True)
    longitude=models.FloatField(null=True,blank=True)
    location=models.ForeignKey(Locations, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Ministries(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Commissions(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class LocalAuthorities(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    electoral_district=models.ForeignKey(Electroaldistrict, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)
    class Meta:
        ordering = ('id', )
class Departments(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    ministry=models.ForeignKey(Ministries, on_delete=models.CASCADE, blank=True, null=True)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Branches(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    department=models.ForeignKey(Departments, on_delete=models.CASCADE, blank=True, null=True)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Divisionalsecretariats(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    admin_district=models.ForeignKey(Ministries, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )


class Policedivisions(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    provice=models.ForeignKey(Provincialcouncils, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Policestations(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    police_division=models.ForeignKey(Policedivisions, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Provincialministries(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    province=models.ForeignKey(Provincialcouncils, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)

    class Meta:
        ordering = ('id', )

class Provincialministrydemartments(models.Model):
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    province_ministry=models.ForeignKey(Provincialministries, on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)
    class Meta:
        ordering = ('id', )

#for EDR Drop down
class Institutes(models.Model):
    code=models.CharField(max_length=50)
    name_sinhala=models.CharField(max_length=200)
    name_tamil=models.CharField(max_length=200)
    name_english=models.CharField(max_length=200)
    institute_type=models.CharField(max_length=50)
    mother_org=models.ForeignKey('self', null=True, related_name='Institutes', on_delete=models.CASCADE)
    status=models.BooleanField(default=True)
    created_time=models.DateTimeField(auto_now_add= True)
    updated_time=models.DateTimeField(auto_now= True)
    class Meta:
        ordering = ('id', )

