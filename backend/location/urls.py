from django.urls import path
from .views import LocationViewset, GramaniladariViewset, PolingdivisionViewset, ElectoraldistrictViewset,AdmindistrictViewset
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register('locations', LocationViewset)
router.register('gdns', GramaniladariViewset)
router.register('polingdivisions',PolingdivisionViewset)
router.register('electoraldistrics',ElectoraldistrictViewset)
router.register('admindistricts',AdmindistrictViewset)
urlpatterns = router.urls