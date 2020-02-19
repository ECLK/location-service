# Generated by Django 3.0.3 on 2020-02-19 03:29

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('location', '0006_auto_20200219_0834'),
    ]

    operations = [
        migrations.RenameField(
            model_name='media_items',
            old_name='lat',
            new_name='latitude',
        ),
        migrations.RenameField(
            model_name='media_items',
            old_name='lon',
            new_name='longitude',
        ),
        migrations.AlterField(
            model_name='media_items',
            name='file_type',
            field=models.CharField(max_length=10),
        ),
        migrations.AlterField(
            model_name='media_items',
            name='media',
            field=models.FileField(upload_to=''),
        ),
    ]
