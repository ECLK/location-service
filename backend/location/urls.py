from django.urls import path
from .views import LocationViewset, GramaniladariViewset, PolingdivisionViewset, ElectoraldistrictViewset,AdmindistrictViewset,MideaItemViewset,SearchLocation,MinistriesViewset, CommissionsViewset, LocalAuthoritiesViewset, DepartmentsViewset, BranchesViewset, DivisionalsecretariatsViewset, ProvincialcouncilsViewset
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register('locations', LocationViewset)
router.register('gdns', GramaniladariViewset)
router.register('polingdivisions', PolingdivisionViewset)
router.register('electoraldistrics', ElectoraldistrictViewset)
router.register('admindistricts', AdmindistrictViewset)
router.register('img', MideaItemViewset)
router.register('search', SearchLocation)
router.register('ministries', MinistriesViewset)
router.register('provincialcouncils', ProvincialcouncilsViewset)
router.register('commissions', CommissionsViewset)
router.register('localauthorities', LocalAuthoritiesViewset)
router.register('departments', DepartmentsViewset)
router.register('branches', BranchesViewset)
router.register('divisionalsecretariats', DivisionalsecretariatsViewset)


urlpatterns = router.urls