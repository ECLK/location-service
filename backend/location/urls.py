from django.urls import path
from .views import LocationViewset, GramaniladariViewset, PolingdivisionViewset, ElectoraldistrictViewset,AdmindistrictViewset,MideaItemViewset,SearchLocation
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register('locations', LocationViewset)
router.register('gdns', GramaniladariViewset)
router.register('polingdivisions',PolingdivisionViewset)
router.register('electoraldistrics',ElectoraldistrictViewset)
router.register('admindistricts',AdmindistrictViewset)
router.register('img',MideaItemViewset)
router.register('search',SearchLocation)

urlpatterns = router.urls